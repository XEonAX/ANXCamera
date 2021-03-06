package com.android.camera.fragment.idcard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.android.camera.R;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.BaseFragmentDelegate;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol;
import com.android.camera.storage.Storage;
import io.reactivex.Completable;
import java.util.List;

public class FragmentIDCard extends BaseFragment implements ModeProtocol.IDCardModeProtocol, ModeProtocol.HandleBackTrace {
    private String mCurrentPictureName = Storage.ID_CARD_PICTURE_1;
    private ViewGroup mRoot;

    private void changeIDCardView(boolean z) {
        IDCardView iDCardView = new IDCardView(getActivity(), z);
        this.mRoot.removeAllViews();
        this.mRoot.addView(iDCardView);
        if (z) {
            setCurrentPictureName(Storage.ID_CARD_PICTURE_1);
        } else {
            setCurrentPictureName(Storage.ID_CARD_PICTURE_2);
        }
    }

    private void setCurrentPictureName(String str) {
        this.mCurrentPictureName = str;
    }

    public void callBackEvent() {
        if (getCurrentPictureName().equals(Storage.ID_CARD_PICTURE_2)) {
            changeIDCardView(true);
        } else {
            ((ModeProtocol.ModeChangeController) ModeCoordinatorImpl.getInstance().getAttachProtocol(179)).changeModeByNewMode(163, 0);
        }
    }

    public String getCurrentPictureName() {
        return this.mCurrentPictureName;
    }

    public int getFragmentInto() {
        return BaseFragmentDelegate.FRAGMENT_ID_CARD;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.fragment_id_card;
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        this.mRoot = (ViewGroup) view;
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
        if (this.mCurrentMode == 182) {
            this.mRoot.setVisibility(0);
            changeIDCardView(true);
        }
    }

    public boolean onBackEvent(int i) {
        if (i != 1 || this.mCurrentMode != 182) {
            return false;
        }
        callBackEvent();
        return true;
    }

    public void onDestroyView() {
        ModeProtocol.BackStack backStack = (ModeProtocol.BackStack) ModeCoordinatorImpl.getInstance().getAttachProtocol(171);
        if (backStack != null) {
            backStack.removeBackStack(this);
        }
        super.onDestroyView();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ModeProtocol.BackStack backStack = (ModeProtocol.BackStack) ModeCoordinatorImpl.getInstance().getAttachProtocol(171);
        if (backStack != null) {
            backStack.addInBackStack(this);
        }
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        super.provideAnimateElement(i, list, i2);
        if (i != 182) {
            this.mRoot.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void register(ModeProtocol.ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        ModeCoordinatorImpl.getInstance().attachProtocol(233, this);
    }

    public void switchNextPage() {
        if (Storage.isIdCardPictureOne(getCurrentPictureName())) {
            changeIDCardView(false);
        }
    }

    /* access modifiers changed from: protected */
    public void unRegister(ModeProtocol.ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        ModeCoordinatorImpl.getInstance().detachProtocol(233, this);
    }
}
