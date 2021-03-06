package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;

public class ResolutionAnchor extends ResolutionNode {
    public static final int BARRIER_CONNECTION = 5;
    public static final int CENTER_CONNECTION = 2;
    public static final int CHAIN_CONNECTION = 4;
    public static final int DIRECT_CONNECTION = 1;
    public static final int MATCH_CONNECTION = 3;
    public static final int UNCONNECTED = 0;
    float computedValue;
    private ResolutionDimension dimension = null;
    private int dimensionMultiplier = 1;
    ConstraintAnchor myAnchor;
    float offset;
    private ResolutionAnchor opposite;
    private ResolutionDimension oppositeDimension = null;
    private int oppositeDimensionMultiplier = 1;
    private float oppositeOffset;
    float resolvedOffset;
    ResolutionAnchor resolvedTarget;
    ResolutionAnchor target;
    int type = 0;

    public ResolutionAnchor(ConstraintAnchor constraintAnchor) {
        this.myAnchor = constraintAnchor;
    }

    /* access modifiers changed from: package-private */
    public void addResolvedValue(LinearSystem linearSystem) {
        SolverVariable solverVariable = this.myAnchor.getSolverVariable();
        ResolutionAnchor resolutionAnchor = this.resolvedTarget;
        if (resolutionAnchor == null) {
            linearSystem.addEquality(solverVariable, (int) (this.resolvedOffset + 0.5f));
        } else {
            linearSystem.addEquality(solverVariable, linearSystem.createObjectVariable(resolutionAnchor.myAnchor), (int) (this.resolvedOffset + 0.5f), 6);
        }
    }

    public void dependsOn(int i, ResolutionAnchor resolutionAnchor, int i2) {
        this.type = i;
        this.target = resolutionAnchor;
        this.offset = (float) i2;
        this.target.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i) {
        this.target = resolutionAnchor;
        this.offset = (float) i;
        this.target.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.target = resolutionAnchor;
        this.target.addDependent(this);
        this.dimension = resolutionDimension;
        this.dimensionMultiplier = i;
        this.dimension.addDependent(this);
    }

    public float getResolvedValue() {
        return this.resolvedOffset;
    }

    public void remove(ResolutionDimension resolutionDimension) {
        ResolutionDimension resolutionDimension2 = this.dimension;
        if (resolutionDimension2 == resolutionDimension) {
            this.dimension = null;
            this.offset = (float) this.dimensionMultiplier;
        } else if (resolutionDimension2 == this.oppositeDimension) {
            this.oppositeDimension = null;
            this.oppositeOffset = (float) this.oppositeDimensionMultiplier;
        }
        resolve();
    }

    public void reset() {
        super.reset();
        this.target = null;
        this.offset = 0.0f;
        this.dimension = null;
        this.dimensionMultiplier = 1;
        this.oppositeDimension = null;
        this.oppositeDimensionMultiplier = 1;
        this.resolvedTarget = null;
        this.resolvedOffset = 0.0f;
        this.computedValue = 0.0f;
        this.opposite = null;
        this.oppositeOffset = 0.0f;
        this.type = 0;
    }

    public void resolve() {
        float f2;
        float f3;
        float f4;
        float f5;
        boolean z = true;
        if (this.state != 1 && this.type != 4) {
            ResolutionDimension resolutionDimension = this.dimension;
            if (resolutionDimension != null) {
                if (resolutionDimension.state == 1) {
                    this.offset = ((float) this.dimensionMultiplier) * resolutionDimension.value;
                } else {
                    return;
                }
            }
            ResolutionDimension resolutionDimension2 = this.oppositeDimension;
            if (resolutionDimension2 != null) {
                if (resolutionDimension2.state == 1) {
                    this.oppositeOffset = ((float) this.oppositeDimensionMultiplier) * resolutionDimension2.value;
                } else {
                    return;
                }
            }
            if (this.type == 1) {
                ResolutionAnchor resolutionAnchor = this.target;
                if (resolutionAnchor == null || resolutionAnchor.state == 1) {
                    ResolutionAnchor resolutionAnchor2 = this.target;
                    if (resolutionAnchor2 == null) {
                        this.resolvedTarget = this;
                        this.resolvedOffset = this.offset;
                    } else {
                        this.resolvedTarget = resolutionAnchor2.resolvedTarget;
                        this.resolvedOffset = resolutionAnchor2.resolvedOffset + this.offset;
                    }
                    didResolve();
                    return;
                }
            }
            if (this.type == 2) {
                ResolutionAnchor resolutionAnchor3 = this.target;
                if (resolutionAnchor3 != null && resolutionAnchor3.state == 1) {
                    ResolutionAnchor resolutionAnchor4 = this.opposite;
                    if (resolutionAnchor4 != null) {
                        ResolutionAnchor resolutionAnchor5 = resolutionAnchor4.target;
                        if (resolutionAnchor5 != null && resolutionAnchor5.state == 1) {
                            if (LinearSystem.getMetrics() != null) {
                                LinearSystem.getMetrics().centerConnectionResolved++;
                            }
                            this.resolvedTarget = this.target.resolvedTarget;
                            ResolutionAnchor resolutionAnchor6 = this.opposite;
                            resolutionAnchor6.resolvedTarget = resolutionAnchor6.target.resolvedTarget;
                            ConstraintAnchor.Type type2 = this.myAnchor.mType;
                            int i = 0;
                            if (!(type2 == ConstraintAnchor.Type.RIGHT || type2 == ConstraintAnchor.Type.BOTTOM)) {
                                z = false;
                            }
                            if (z) {
                                f3 = this.target.resolvedOffset;
                                f2 = this.opposite.target.resolvedOffset;
                            } else {
                                f3 = this.opposite.target.resolvedOffset;
                                f2 = this.target.resolvedOffset;
                            }
                            float f6 = f3 - f2;
                            ConstraintAnchor constraintAnchor = this.myAnchor;
                            ConstraintAnchor.Type type3 = constraintAnchor.mType;
                            if (type3 == ConstraintAnchor.Type.LEFT || type3 == ConstraintAnchor.Type.RIGHT) {
                                f5 = f6 - ((float) this.myAnchor.mOwner.getWidth());
                                f4 = this.myAnchor.mOwner.mHorizontalBiasPercent;
                            } else {
                                f5 = f6 - ((float) constraintAnchor.mOwner.getHeight());
                                f4 = this.myAnchor.mOwner.mVerticalBiasPercent;
                            }
                            int margin = this.myAnchor.getMargin();
                            int margin2 = this.opposite.myAnchor.getMargin();
                            if (this.myAnchor.getTarget() == this.opposite.myAnchor.getTarget()) {
                                f4 = 0.5f;
                                margin2 = 0;
                            } else {
                                i = margin;
                            }
                            float f7 = (float) i;
                            float f8 = (float) margin2;
                            float f9 = (f5 - f7) - f8;
                            if (z) {
                                ResolutionAnchor resolutionAnchor7 = this.opposite;
                                resolutionAnchor7.resolvedOffset = resolutionAnchor7.target.resolvedOffset + f8 + (f9 * f4);
                                this.resolvedOffset = (this.target.resolvedOffset - f7) - (f9 * (1.0f - f4));
                            } else {
                                this.resolvedOffset = this.target.resolvedOffset + f7 + (f9 * f4);
                                ResolutionAnchor resolutionAnchor8 = this.opposite;
                                resolutionAnchor8.resolvedOffset = (resolutionAnchor8.target.resolvedOffset - f8) - (f9 * (1.0f - f4));
                            }
                            didResolve();
                            this.opposite.didResolve();
                            return;
                        }
                    }
                }
            }
            if (this.type == 3) {
                ResolutionAnchor resolutionAnchor9 = this.target;
                if (resolutionAnchor9 != null && resolutionAnchor9.state == 1) {
                    ResolutionAnchor resolutionAnchor10 = this.opposite;
                    if (resolutionAnchor10 != null) {
                        ResolutionAnchor resolutionAnchor11 = resolutionAnchor10.target;
                        if (resolutionAnchor11 != null && resolutionAnchor11.state == 1) {
                            if (LinearSystem.getMetrics() != null) {
                                LinearSystem.getMetrics().matchConnectionResolved++;
                            }
                            ResolutionAnchor resolutionAnchor12 = this.target;
                            this.resolvedTarget = resolutionAnchor12.resolvedTarget;
                            ResolutionAnchor resolutionAnchor13 = this.opposite;
                            ResolutionAnchor resolutionAnchor14 = resolutionAnchor13.target;
                            resolutionAnchor13.resolvedTarget = resolutionAnchor14.resolvedTarget;
                            this.resolvedOffset = resolutionAnchor12.resolvedOffset + this.offset;
                            resolutionAnchor13.resolvedOffset = resolutionAnchor14.resolvedOffset + resolutionAnchor13.offset;
                            didResolve();
                            this.opposite.didResolve();
                            return;
                        }
                    }
                }
            }
            if (this.type == 5) {
                this.myAnchor.mOwner.resolve();
            }
        }
    }

    public void resolve(ResolutionAnchor resolutionAnchor, float f2) {
        if (this.state == 0 || !(this.resolvedTarget == resolutionAnchor || this.resolvedOffset == f2)) {
            this.resolvedTarget = resolutionAnchor;
            this.resolvedOffset = f2;
            if (this.state == 1) {
                invalidate();
            }
            didResolve();
        }
    }

    /* access modifiers changed from: package-private */
    public String sType(int i) {
        return i == 1 ? "DIRECT" : i == 2 ? "CENTER" : i == 3 ? "MATCH" : i == 4 ? "CHAIN" : i == 5 ? "BARRIER" : "UNCONNECTED";
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, float f2) {
        this.opposite = resolutionAnchor;
        this.oppositeOffset = f2;
    }

    public void setOpposite(ResolutionAnchor resolutionAnchor, int i, ResolutionDimension resolutionDimension) {
        this.opposite = resolutionAnchor;
        this.oppositeDimension = resolutionDimension;
        this.oppositeDimensionMultiplier = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        if (this.state != 1) {
            return "{ " + this.myAnchor + " UNRESOLVED} type: " + sType(this.type);
        } else if (this.resolvedTarget == this) {
            return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedOffset + "]  type: " + sType(this.type);
        } else {
            return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedTarget + ":" + this.resolvedOffset + "] type: " + sType(this.type);
        }
    }

    public void update() {
        ConstraintAnchor target2 = this.myAnchor.getTarget();
        if (target2 != null) {
            if (target2.getTarget() == this.myAnchor) {
                this.type = 4;
                target2.getResolutionNode().type = 4;
            }
            int margin = this.myAnchor.getMargin();
            ConstraintAnchor.Type type2 = this.myAnchor.mType;
            if (type2 == ConstraintAnchor.Type.RIGHT || type2 == ConstraintAnchor.Type.BOTTOM) {
                margin = -margin;
            }
            dependsOn(target2.getResolutionNode(), margin);
        }
    }
}
