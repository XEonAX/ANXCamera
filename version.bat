call ..\ANXMiuiPortTools\versionizer.exe v=".\VERSION" incrementversionname=true incrementversion=true

..\ANXMiuiPortTools\versionizer.exe v=".\VERSION" am=".\src\ANXCamera\AndroidManifest.xml"
..\ANXMiuiPortTools\versionizer.exe v=".\VERSION" am=".\src\ANXExtraPhoto\AndroidManifest.xml"

..\ANXMiuiPortTools\versionizer.exe v=".\VERSION" mm=".\src\ANXCameraUnity\module.prop"

..\ANXMiuiPortTools\versionizer.exe v=".\VERSION" mm=".\src\ANXVendorLibs\module.prop"
