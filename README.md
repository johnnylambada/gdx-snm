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

## License

Apache for now.
