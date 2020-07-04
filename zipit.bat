for /f "delims== tokens=1,2" %%G in (VERSION) do set anx%%G=%%H
echo %anxversion%


xcopy /s /y .\out\ANXCamera.apk .\src\ANXCameraUnity\system\priv-app\ANXCamera
xcopy /s /y .\out\ANXExtraPhoto.apk .\src\ANXCameraUnity\system\priv-app\ANXExtraPhoto
del .\out\ANXCameraUnity.zip
del .\out\ANXCameraUnity_*.zip
"C:\Program Files\7-Zip\7z.exe" a -tzip .\out\ANXCameraUnity.zip .\src\ANXCameraUnity\*
copy .\out\ANXCameraUnity.zip /B .\out\ANXCameraUnity_%anxversion%.zip
adb push .\out\ANXCameraUnity.zip  /sdcard/zips

REM del .\out\ANXVendorLibs.zip
REM del .\out\ANXVendorLibs_*.zip
REM "C:\Program Files\7-Zip\7z.exe" a -tzip .\out\ANXVendorLibs.zip .\src\ANXVendorLibs\*
REM copy .\out\ANXVendorLibs.zip /B .\out\ANXVendorLibs_%anxversion%.zip
REM adb push .\out\ANXVendorLibs.zip  /sdcard/zips
