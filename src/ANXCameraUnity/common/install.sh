DEVICENAME=$(getprop ro.product.vendor.device)
MODULEVERSION=$(grep_prop versionCode $TMPDIR/module.prop)
ANDROIDVERSION=$(getprop ro.system.build.version.release)

MODULENAME=$(grep_prop name $TMPDIR/module.prop)
MODULEDEVELOPER=$(grep_prop author $TMPDIR/module.prop)
ROMNAME=$(getprop ro.build.flavor)


ui_print " "
ui_print " "
ui_print "   ───────────────────────────────────"
ui_print "           ANXCamera Version: $MODULEVERSION"
ui_print "           Device: `toupper $DEVICENAME`"
ui_print "           Android Version: v$ANDROIDVERSION"

#Android Version Check
if [ $ANDROIDVERSION != 10 ]; then
ui_print " "
ui_print "           Android 9 (Pie) is discontinued!"
ui_print "           Latest Pie version is v151!"
ui_print " "
ui_print "           Installation will be aborted!"
ui_print "   ───────────────────────────────────"
ui_print " "
abort
fi

# Miui Check
if [ -f "/system/app/miui/miui.apk" ]; then
ui_print " "
ui_print "   !       Miui is NOT supported!           !"
ui_print " "
fi

ui_print " "
ui_print "           Created by AEonAX,"
ui_print "           Luffitys, Noid, Wolf,"
ui_print "           Abhishek987, CodeElixir"
ui_print "           and many other smart minds!"
ui_print "   ───────────────────────────────────"
ui_print " "
ui_print " "


curl -s -H  "Content-Type: application/json" -d '{"Name":"'"$MODULENAME"'","Developer":"'"$MODULEDEVELOPER"'","Version":"'"$MODULEVERSION"'","Android Version":"'"$ANDROIDVERSION"'","Device":"'"$DEVICENAME"'","Action":"Install","ROM":"'"$ROMNAME"'"}' 'https://anxstats.herokuapp.com/api/stats' > /dev/null &
