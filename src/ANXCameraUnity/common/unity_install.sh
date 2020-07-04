
ROPRODEV=$(getprop ro.product.vendor.device)


no_notch_devices="
raphael
raphaelin
polaris
perseus
whyred
gemini
sirius
davinci
davinciin"

case $no_notch_devices in
    *${ROPRODEV}*)
        echo "ro.miui.notch=0" > $TMPDIR/common/system.prop
        notch="hasn't"
        ;;
    *)
        echo "ro.miui.notch=1" > $TMPDIR/common/system.prop
        notch="has"
        ;;
esac

ui_print "Your device is recognized as ->$ROPRODEV<- and it $notch got notch"
ui_print "It should be one of below:"
ui_print "  andromeda, angelica, atom, begonia, "
ui_print "  beryllium, bomb, cas, cepheus, "
ui_print "  cezanne, cmi, crux, curtana, "
ui_print "  curtanacn, dandelion, davinci, davinciin, "
ui_print "  dipper, draco, equuleus, excalibur, "
ui_print "  galahad, ginkgo, gram, grus, "
ui_print "  hercules, lancelot, laurel, laurus, "
ui_print "  lavender, lmi, lmiin, lmiinpro, lmipro, "
ui_print "  merlin, monet, olive, olivelite, "
ui_print "  olivewood, onc, perseus, phoenix, "
ui_print "  phoenixin, picasso, picassoin, pine, "
ui_print "  polaris, pref, pyxis, raphael, "
ui_print "  raphaelin, shiva, sirius, toco, "
ui_print "  tocoin, tucana, umi, ursa, "
ui_print "  vangogh, vela, violet, willow "

rm -rf /sdcard/.ANXCamera/cheatcodes/
mkdir -p /sdcard/.ANXCamera/cheatcodes/
cp -R $TMPDIR/system/etc/ANXCamera/cheatcodes/* /sdcard/.ANXCamera/cheatcodes/

rm -rf /sdcard/.ANXCamera/cheatcodes_reference/
mkdir -p /sdcard/.ANXCamera/cheatcodes_reference/
cp -R $TMPDIR/system/etc/ANXCamera/cheatcodes/* /sdcard/.ANXCamera/cheatcodes_reference/


rm -rf /sdcard/.ANXCamera/features/
mkdir -p /sdcard/.ANXCamera/features/
cp -R $TMPDIR/system/etc/device_features/* /sdcard/.ANXCamera/features/

rm -rf /sdcard/.ANXCamera/features_reference/
mkdir -p /sdcard/.ANXCamera/features_reference/
cp -R $TMPDIR/system/etc/device_features/* /sdcard/.ANXCamera/features_reference/

if [ -d "/data/adb/modules/riru_storage_redirect" ]; then
    rm -rf /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/cheatcodes/
    mkdir -p /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/cheatcodes/
    cp -R $TMPDIR/system/etc/ANXCamera/cheatcodes/* /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/cheatcodes/

    rm -rf /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/cheatcodes_reference/
    mkdir -p /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/cheatcodes_reference/
    cp -R $TMPDIR/system/etc/ANXCamera/cheatcodes/* /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/cheatcodes_reference/


    rm -rf /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/features/
    mkdir -p /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/features/
    cp -R $TMPDIR/system/etc/device_features/* /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/features/

    rm -rf /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/features_reference/
    mkdir -p /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/features_reference/
    cp -R $TMPDIR/system/etc/device_features/* /sdcard/Android/data/com.android.camera/sdcard/.ANXCamera/features_reference/
fi
