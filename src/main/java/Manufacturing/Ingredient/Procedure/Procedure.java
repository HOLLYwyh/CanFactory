package Manufacturing.Ingredient.Procedure;

import Manufacturing.Ingredient.BaseIngredient;
import Manufacturing.Ingredient.Ingredient;
import Presentation.Protocol.OutputManager;

/**
 * TODO:此处写Procedure类的描述
 *
 * @author 卓正一
 * @date 2021/10/24 5:07 PM
 */
public class Procedure extends BaseIngredient {
    @Override
    public String zhCnDescription() {
        return super.zhCnDescription() + dealtIngredient.zhCnDescription();
    }

    @Override
    public String zhTwDescription() {
        return super.zhTwDescription() + dealtIngredient.zhTwDescription();
    }

    @Override
    public String enDescription() {
        return super.enDescription()
                + (super.enDescription().isEmpty() ? "" : " ")
                + dealtIngredient.enDescription();
    }

    public Procedure(Ingredient ingredient) {
        this.dealtIngredient = ingredient;
    }

    protected Ingredient dealtIngredient;

    @Override
    public String showContents() {
        return OutputManager.getInstance().selectStringForCurrentLanguage(
                super.zhCnDescription(),
                super.zhTwDescription(),
                super.enDescription() + " "
        ) + dealtIngredient.showContents();
    }
}
