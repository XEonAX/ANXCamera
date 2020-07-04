java  -jar ..\ANXMiuiPortTools\signapk.jar ..\ANXMiuiPortTools\testkey.x509.pem ..\ANXMiuiPortTools\testkey.pk8 .\out\ANXCamera-Unsigned.apk .\out\ANXCamera-Unaligned.apk
..\ANXMiuiPortTools\zipalign.exe -f 4  .\out\ANXCamera-Unaligned.apk .\out\ANXCamera.apk

java  -jar ..\ANXMiuiPortTools\signapk.jar ..\ANXMiuiPortTools\testkey.x509.pem ..\ANXMiuiPortTools\testkey.pk8 .\out\ANXExtraPhoto-Unsigned.apk .\out\ANXExtraPhoto-Unaligned.apk
..\ANXMiuiPortTools\zipalign.exe -f 4  .\out\ANXExtraPhoto-Unaligned.apk .\out\ANXExtraPhoto.apk
