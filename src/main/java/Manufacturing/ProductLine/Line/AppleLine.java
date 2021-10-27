package Manufacturing.ProductLine.Line;

import Manufacturing.Ingredient.BaseIngredient;
import Manufacturing.Ingredient.Ingredient;
import Manufacturing.Machine.Machine;
import Manufacturing.ProductLine.FruitLine;
import Manufacturing.ProductLine.Producer.AppleProducer;
import Presentation.Protocol.OutputManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO:苹果罐头生产线类
 *
 * @author 孟繁霖
 * @date 2021-10-12 8:33
 */
public class AppleLine implements FruitLine {

    private List<Machine> pretreatmentMachine = new ArrayList<>();

    public AppleLine() {

    }

    public AppleLine(Machine ... machines) {
        pretreatmentMachine.addAll(Arrays.stream(machines).collect(Collectors.toList()));
    }

    @Override
    public List<BaseIngredient> preTreat(List<BaseIngredient> baseIngredientList) {

        OutputManager.getInstance().print(
                "**********正在对苹果进行预处理*********",
                "**********正在對蘋果進行預處理*********",
                "**********Apple is being preprocessed*********");
        baseIngredientList = pretreatmentApp.filterTreat(baseIngredientList);
        pretreatmentApp.peel(baseIngredientList);
        pretreatmentApp.disinfect(baseIngredientList);
        OutputManager.getInstance().print(
                "*************苹果预处理完成***********",
                "*************蘋果預處理完成***********",
                "********Apple preprocessing is completed*****");
        return baseIngredientList;
    }

    @Override
    public void produce(int count, String produceManner) {
        OutputManager.getInstance().print(
                "**********正在对苹果进行加工**********",
                "**********正在對蘋果進行加工**********",
                "**********Apples are being processed**********");
        AppleProducer appleProducer = new AppleProducer(produceManner);
        appleProducer.produce();
        OutputManager.getInstance().print(
                "共生产" + count + "个苹果罐头",
                "共生產" + count + "個蘋果罐頭",
                "Totally produced" + count + "apple can!");
    }

    @Override
    public String getConcreteName() {
        return "appleLine";
    }
}
