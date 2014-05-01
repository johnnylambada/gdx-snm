export ANDROID_AAPT=$(find $ANDROID_HOME/build-tools -name aapt | sort | tail -1)
function hmm() {
cat <<EOF
Invoke ". scripts/envsetup.sh" from your shell to add the following
functions to your environment:
EOF
    T=$(gettop)
    sort $T/.hmm |awk -F @ '{printf "%-15s %s\n",$1,$2}'
cat <<EOF

Some fun things to try:
cdsp && m -c -r && install && start     # B&I service provider release version
cdmember && m -c -r && install && start # B&I member release version
#squeeky -r  # DON'T DO THIS UNLESS YOU MEAN IT - makes your repo like new -- deletes yer stuff
EOF
}

function gettop
{
    local TOPFILE=scripts/envsetup.sh
    if [ -n "$TOP" -a -f "$TOP/$TOPFILE" ] ; then
        echo $TOP
    else
        if [ -f $TOPFILE ] ; then
            # The following circumlocution (repeated below as well) ensures
            # that we record the true directory name and not one that is
            # faked up with symlink names.
            PWD= /bin/pwd
        else
            # We redirect cd to /dev/null in case it's aliased to
            # a command that prints something as a side-effect
            # (like pushd)
            local HERE=$PWD
            T=
            while [ \( ! \( -f $TOPFILE \) \) -a \( $PWD != "/" \) ]; do
                cd .. > /dev/null
                T=`PWD= /bin/pwd`
            done
            cd $HERE > /dev/null
            if [ -f "$T/$TOPFILE" ]; then
                echo $T
            fi
        fi
    fi
}
T=$(gettop)
rm -f $T/.hmm $T/.hmmv
echo "gettop@display the top directory" >> $T/.hmm

function croot()
{
    T=$(gettop)
    if [ "$T" ]; then
        cd $(gettop)
    else
        echo "Couldn't locate the top of the tree.  Try setting TOP."
    fi
}
echo "croot@Change back to the top dir" >> $T/.hmm

function sgrep()
{
    find -E . -not -regex ".*/.git/.*" -a -not -regex ".*/build/.*" -type f -iregex '.*\.(c|h|cpp|S|java|xml|sh|mk)' -print0 | xargs -0 grep --color -n "$@"
}
echo 'sgrep@Grep through all sources (c|h|cpp|S|jav|xml|sh|mk)' >> $T/.hmm

function jgrep()
{
    find . -not -regex ".*/.git/.*" -a -not -regex ".*/build/.*" -type f -name "*\.java" -print0 | xargs -0 grep --color -n "$@"
}
echo 'jgrep@Grep through all java sources' >> $T/.hmm

function resgrep()
{
    find $(find . -not -regex ".*/.git/.*" -a -not -regex ".*/build/.*" -a -name res -type d) -type f -name "*.xml" -print0 | xargs -0 grep --color -n "$@"
}
echo 'resgrep@Grep through all resources (xml)' >> $T/.hmm

function m
{
	local clean=""
	local release=debug
	local rename=
	while [ -n "$1" ]; do
		case $1 in
		-c)	clean=clean ;;
		-n) shift
            rename="-Dmanifestpackage=$1"
            ;;
		-r)	release=release ;;
		esac
		shift
	done
	(
	inandroidproject &&
	echo ant $clean $release $rename &&
	time ant $clean $release $rename
	)
}
echo 'm@make the app in this dir [-c]/*clean*/ [-r]/*release*/ [-n]/*rename-apk*/' >> $T/.hmm

function myapk
{
	local release=debug
	while [ -n "$1" ]; do
		case $1 in
		-r)	release=release ;;
		esac
		shift
	done
	inandroidproject &&
	find bin -name "*.apk" -and -not -name "*unaligned.apk" -and -not -name "*unsigned.apk" | grep $release
}
echo 'myapk@Display the filename of the debug apk for this app. -r for the release version.' >> $T/.hmm

function mypackage
{
	$ANDROID_AAPT list -a $(myapk $* 2>/dev/null) | sed -n "/^Package Group[^s]/s/.*name=//p"
}
echo 'mypackage@Display the package' >> $T/.hmm

function myversion
{
	$ANDROID_AAPT list -a $(myapk $* 2>/dev/null) | sed -n 's/.*versionName.*Raw: "\([^"]*\)".*/\1/p'
}
echo 'myversion@Display the version' >> $T/.hmm

function install
{
	local serial=
	while [ -n "$1" ]; do
		case $1 in
		-s)
            shift
            serial="-s $1"
            ;;
		esac
		shift
	done
	adb $serial install -r $(myapk $*)
}
echo 'install@Install the apk for this app' >> $T/.hmm

function uninstall
{
	local serial=
	while [ -n "$1" ]; do
		case $1 in
		-s)
            shift
            serial="-s $1"
            ;;
		esac
		shift
	done
	adb $serial uninstall $(mypackage $*)
}
echo 'uninstall@remove the apk for this app. -s SERIAL' >> $T/.hmm

function start
{
	local apk
	local serial=
	while [ -n "$1" ]; do
		case $1 in
		-s)
            shift
            serial="-s $1"
            ;;
		esac
		shift
	done
	apk=$(myapk $* 2>/dev/null) &&
	local run=$($ANDROID_AAPT dump badging $apk | awk "/^package:/{gsub(/^[^']*'/,\"\");gsub(/'.*/,\"\");package=\$1;}/^launchable-activity:/{gsub(/^[^']*'/,\"\");gsub(/'.*/,\"\");activity=\$1;}END{print package\"/\"activity}") && adb $serial shell am start -n $run
}
echo 'start@Start the app on the device' >> $T/.hmm

function inandroidproject
{
	if [ ! -e AndroidManifest.xml.m4 ]; then
		echo >&2 "You're not in an android project"
		return 1
	fi
	return 0
}

function squeaky
{
	local remove=cat
	while [ -n "$1" ]; do
		case $1 in
		-r)	remove="xargs rm -rf" ;;
		esac
		shift
	done
	git clean -ndX | sed 's/^Would remove //' | egrep -v "(local.properties|\.iml|\.hmm)$" | sort | $remove
}
echo 'squeaky@Show all non-git files. Use the -r flag to actually remove them' >> $T/.hmm

function testfairy() {
	inandroidproject &&
    curl https://app.testfairy.com/api/upload \
      -F apk_file=@$(myapk -r) \
      -F api_key=$(echo "include(config.m4)__TESTFAIRY_API_TOKEN" | m4 -I $(gettop)) \
      -F comment="$(git log --no-merges -5 --oneline)" \
      -F metrics='cpu,network' \
      -F testers_groups='all'
}
echo 'testfairy@Upload the current release apk to testfairy.' >> $T/.hmm

function mailapk() {
	inandroidproject &&
    curl $(echo "include(config.m4)__MAILGUN_URL" | m4 -I $(gettop)) \
      -s \
      --user $(echo "include(config.m4)__MAILGUN_API_KEY" | m4 -I $(gettop)) \
      -F from="$(echo "include(config.m4)__MAILGUN_FROM" | m4 -I $(gettop))" \
      -F to="$(echo "include(config.m4)__MAILGUN_TO" | m4 -I $(gettop))" \
      -F subject="New Android app: $(myversion -r)" \
      -F text="$(git log --no-merges -5 --oneline)" \
      -F attachment=@$(myapk -r)
}
echo 'mailapk@Mail the current APK' >> $T/.hmm

function isemulatorup() {
    local bootanim=$(adb -e shell getprop init.svc.bootanim 2>&1)
    [[ "$bootanim" =~ "stopped" ]] && return 0
	echo >&2 "Emulator is not running"
    return 1
}
echo 'isemulatorup@is the emulator up?' >> $T/.hmm

function gencards() {
    local path=art/gen/svg
    (
    croot
    rm -rf $path
    mkdir -p $path
    cd $path
    ../../../scripts/gencards
    )
}
echo 'gencards@generate the SVG cards' >> $T/.hmm

function svg2png() {
	local mag=1
	while [ -n "$1" ]; do
		case $1 in
		-m)	shift;mag=$1 ;;
		esac
		shift
	done
    (
    croot
    local dest=art/gen/png/$mag
    rm -rf $dest
    mkdir -p $dest
    for svgpath in $(gfind art/gen/svg -name "*.svg"); do
        file=$(basename $svgpath | sed 's/\.svg//')
        rsvg-convert $svgpath -z $mag -o $dest/$file.png
    done
    )
}
echo 'svg2png@Convert svgs in art/svg to art/gen/x where x is the -m mag' >> $T/.hmm

function mkassets() {
    (
    local classpath=tools/gdx-tools.jar:tools/gdx.jar
    local mag="$(echo "include(config.m4)_MAGNIFICATION" | m4 -I $(gettop))"
    croot
    rm -rf android/assets
    mkdir -p android/assets
    java -classpath $classpath com.badlogic.gdx.tools.texturepacker.TexturePacker art/gen/png/$mag android/assets cards
    )
}
echo 'mkassets@Create the assets directory' >> $T/.hmm

function generate() {
    local mag="$(echo "include(config.m4)_MAGNIFICATION" | m4 -I $(gettop))"
    gencards
    svg2png -m $mag
    mkassets
}
echo 'generate@Generate everything that needs generating' >> $T/.hmm

unset T f
