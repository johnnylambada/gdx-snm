## Synopsis

A play by play of how to make a cross platofrm Spite and Malice card game using libGDX.

## Motivation

I have a terrible memory. Here's how I remember how I did it.

## Project start

From libgdx's [Project Setup for Gradle](https://github.com/libgdx/libgdx/wiki/Project-Setup-Gradle) document:

```
mkdir snm
cd snm
git init
wget http://libgdx.badlogicgames.com/nightlies/dist/gdx-setup.jar
java -jar gdx-setup.jar --dir . --name spite-and-malice --package sigseg.game.snm --mainClass SNMGame --sdkLocation $ANDROID_HOME
./gradlew :desktop:run
vi README.md # You're reading the results
```

## Configure IntelliJ

1. Don't use Android Studio
2. Import Project -> build.gradle in root
3. Configure desktop run:
    * Down arrow to left of Run button
    * Edit Configurations...
    * + to add new configuration
    * Application
    * Name: Desktop
    * Main class: (hit ... on far right): DesktopLauncher
    * Working directory: <what's/already/there/plus>/android/assets
    * Use classpath of module: desktop
    * Apply. Ok.
4. Now you should be able to run the game on the desktop.

## License

Apache for now.
