# ANXCamera


MiuiCamera Port for Beryllium

##  Most up-to-date wiki https://github.com/nckmml/ANXCamera/wiki

Self sufficient repository to decompile to smali, recompile, sign, decompile to java, push to device app port.

miui_PHOENIXININGlobal_V11.0.4.0.QGHINXM_fad80cd58f_10.0 was used as src.

Recommended to open this Repository in VSCode

Also requires Java 1.7 or 1.8, and 7-zip.

Instructions for Development:

 1. Clone this repository
 2. Run redo.bat 
 3. Start porting

  
Instructions for Testing:

 1. Download the `zip` from https://camera.aeonax.com/
 2. Install the `zip` with Magisk, or with recovery
 3. Reboot Once, if it doesn't work properly, reboot twice.
 4. Start Testing


Special Thanks to
Abhishek Aggarwal (https://github.com/TheScarastic) for bringing this up to Beta version
Mustang_ssc (https://github.com/Mustang-ssc) for his help in adding support for other devices
Amogha Maiya (https://github.com/amog787) for sponsoring, and all-round help
Sandeep (https://github.com/CodeElixir) for help with the libs
Psygarden (https://forum.xda-developers.com/member.php?u=7645131) for his general help. 



## Steps to Port MiuiCamera from scratch:
1. Unpack System of Miui ROM
   1. Full ROM could be found [here](https://c.mi.com/global/miuidownload/index)
   2. Use [SUP tools](https://github.com/jamflux/SUR) to unpack System partition 
2. Clone https://github.com/XEonAX/ANXMiuiPortTools to the same parent folder where ANXCamera10 repo located
3. Setup Original files for Decompiling
   1. Copy following to ANXCamera\orig\MiuiFrameworks. Files to be taken from Unpacked `system` from ROM above 
      1. framework\framework-res.apk
      2. app\miui\miui.apk
      3. framework\framework-ext-res\framework-ext-res.apk
      4. app\miuisystem\miuisystem.apk
   2. Copy following to ANXCamera\orig
      1. priv-app\MiuiCamera\MiuiCamera.apk
4. Prepare APKTool for decompiling
   1. Install above framework files by running following commands
      1. `.\preparefw`
5. Decompile MiuiCamera by running
   1. `.\decompile`
   2. Meaning of APKTool Parameters
      1. d, decode
      2. -p, --frame-path <DIR>
      3. -f, --force
      4. -b, --no-debug-info
      5. -o, --output <DIR>
6. Open `src\ANXCamera\AndroidManifest.xml` and format the document
7. First Compile Attempt
   1. Run `recompile.bat` just to check whether we are able to recompile without any modification
   2. Run `sign.bat` to sign and zipalign (requires Java 8 JRE, will throw errors in JDK11)
   3. Run `jadx.bat` to create java code from compiled apk. This fails, don't worry, it does whatever it can
8. Next we decompile the required fw files
   1. Copy latest baksmali to extracted rom destination
   2. Disable some required libs. Open `src\ANXCamera\AndroidManifest.xml`
      1. Find the `uses-library` XML Nodes. We need to disable this
      2. We will skip `miui-stat.jar` as we will disable miui-stats from sending data to miui.
   3. Open a cmd inside ANXCamera10 repo folder and run the following:
```
   java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\framework E:\Dev\Poco_X3\SUR\01-Project\system\system\framework\framework.jar
java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\miui E:\Dev\Poco_X3\SUR\01-Project\system\system\app\miui\miui.apk
java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\miuisystem E:\Dev\Poco_X3\SUR\01-Project\system\system\app\miuisys
```
   4. Extract classes for framework libraries:
```
java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\android-support-v7-recyclerview E:\Dev\Poco_X3\SUR\01-Project\system\system\framework\android-support-v7-recyclerview.jar
java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\android-support-v13 E:\Dev\Poco_X3\SUR\01-Project\system\system\framework\android-support-v13.jar
java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\gson E:\Dev\Poco_X3\SUR\01-Project\system\system\framework\gson.jar
java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\volley E:\Dev\Poco_X3\SUR\01-Project\system\system\framework\volley.jar
java -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o E:\Dev\Poco_X3\ANXCamera10\decompiled\zxing E:\Dev\Poco_X3\SUR\01-Project\system\system\framework\zxing.jar
```

9. Now we will add **few** of the above decompiles libs to our code *(this step needs clarification. Files listed in copymiuiclasses.bat might give you some clues)*
   1. Create a folder `src\ANXCamera\smali_classes10`
   2. Copy the **contents** of 
      1. `<path to deodex destination>\android-support-v7-recyclerview`
      2. `<path to deodex destination>\android-support-v13`
      3. `<path to deodex destination>\gson`
      4. `<path to deodex destination>\volley`
      5. `<path to deodex destination>\zxing`
   3. to `src\ANXCamera\smali_classes2`. It should finally contain two folders
      1. `android` and `com`
   If you run into "ExceptionWithContext: Unsigned short value out of range" error during compilation that means too many classes were packed inside smali_classes folder. Create a new one and split classes between two
10.  Set required = false in AndroidManifest of these libs as their code is now included
11. Add missing smali files from decompiled miui rom
12. Add native libs
13. Edit Smali
   4. ...

