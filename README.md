# ANXCamera
MiuiCamera Port for Xiaomi's Devices

Self sufficient repository to decompile to smali, recompile, sign, decompile to java, push to device app port.

miui_CMI_20.5.7_fc0b456e4b_10.0.zip was used as src.

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
1. Unpack System/Vendor of Miui ROM
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
7. Next we decompile the fw files
   1. Copy `baksmali-2.3.4` to extracted rom destination
   2. We will decompile framework files as mentioned in `backsmalifw.bat`
8. Now we will add entire `Miui Framework` of the decompiled above to our code
   1. Copy following folders to `src\ANXCamera\` 
      1. `G:\ROOT\cmi\smali\framework\smali_classes15`
      2. `G:\ROOT\cmi\smali\framework\smali_classes16`
      3. `G:\ROOT\cmi\smali\framework\smali_classes17`
      4. `G:\ROOT\cmi\smali\framework\smali_classes18`
      5. `G:\ROOT\cmi\smali\miui\smali_classes19`
      6. `G:\ROOT\cmi\smali\miuisystem\smali_classes20`
9. Disable all required libs. Open `src\ANXCamera\AndroidManifest.xml`
   1. Find the `uses-library` XML Nodes.
   2. Add/Set `android:required="false"`
10. Add native libs
11. Edit Smali
   3. ...

