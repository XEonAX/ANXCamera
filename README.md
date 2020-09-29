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



Steps to Port MiuiCamera from scratch:
1. Unpack System of Miui ROM
2. Setup Original files for Decompiling
   1. Copy following to ANXCamera\orig\MiuiFrameworks. Files to be taken from Unpacked `system` from ROM above 
      1. framework\framework-res.apk
      2. app\miui\miui.apk
      3. framework\framework-ext-res\framework-ext-res.apk
      4. app\miuisystem\miuisystem.apk
   2. Copy following to ANXCamera\orig
      1. priv-app\MiuiCamera\MiuiCamera.apk
3. Prepare APKTool for decompiling
   1. Install above framework files by running following commands
      1. `.\preparefw`
4. Decompile MiuiCamera by running
   1. `.\decompile`
   2. Meaning of APKTool Parameters
      1. d, decode
      2. -p, --frame-path <DIR>
      3. -f, --force
      4. -b, --no-debug-info
      5. -o, --output <DIR>
5. Open `src\ANXCamera\AndroidManifest.xml` and format the document
6. First Compile Attempt
   1. Run `recompile.bat` just to check whether we are able to recompile without any modification
   2. Run `sign.bat` to sign and zipalign
   3. Run `jadx.bat` to create java code from compiled apk. This fails, don't worry, it does whatever it can
7. Next we decompile the required fw files
   1. Copy latest baksmali to extracted rom destination
   2. Disable some required libs. Open `src\ANXCamera\AndroidManifest.xml`
      1. Find the `uses-library` XML Nodes. We need to disable this
      2. We will skip `miui-stat.jar` as we will disable miui-stats from sending data to miui.
   3. Open a cmd inside `<path to deodex destination>` folder. And run the following:
      1. `java  -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o G:\ROOT\phoenix\framework G:\ROOT\phoenix\system\system\framework\framework.jar`
      2. `java  -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o G:\ROOT\phoenix\miui G:\ROOT\phoenix\system\system\app\miui\miui.apk`
      3. `java  -jar ..\ANXMiuiPortTools\apktool.jar d -p ..\ANXMiuiPortTools\MiuiFrameworks -f -b -o G:\ROOT\phoenix\miuisystem G:\ROOT\phoenix\system\system\app\miuisystem\miuisystem.apk`
8. Now we will add **few** of the above decompiles libs to our code
   1. Create a folder `src\ANXCamera\smali_classes5`
   2. Copy the **contents** of 
      1. `<path to deodex destination>\android-support-v7-recyclerview`
      2. `<path to deodex destination>\android-support-v13`
      3. `<path to deodex destination>\gson`
      4. `<path to deodex destination>\volley`
      5. `<path to deodex destination>\zxing`
   3. to `src\ANXCamera\smali_classes2`. It should finally contain two folders
      1. `android` and `com`
9.  Set required = false in AndroidManifest of these libs as their code is now included
10. Add missing smali files from decompiled miui rom
11. Add native libs
12. Edit Smali
   4. ...

