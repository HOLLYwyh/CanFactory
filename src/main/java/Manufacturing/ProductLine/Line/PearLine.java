package Manufacturing.ProductLine.Line;

import Manufacturing.ProductLine.Fruit.RawMaterial;
import Manufacturing.ProductLine.FruitLine;
import Manufacturing.ProductLine.adapter.PearProducer;
import Presentation.Protocol.OutputManager;

import java.util.List;

/**
 * TODO:梨罐头生产线类
 *
 * @author 孟繁霖
 * @date 2021-10-12 8:32
 */
public class PearLine implements FruitLine {
    @Override
    public List<RawMaterial> preTreat(List<RawMaterial> rawMaterialList) {
        OutputManager.getInstance().errorMassage(
                "******正在对梨进行预处理********",
                "******正在對梨進行預處理********",
                "******Pears are being preprocessed********");
        rawMaterialList = pretreatmentApp.filterTreat(rawMaterialList);
        pretreatmentApp.peel(rawMaterialList);
        pretreatmentApp.disinfect(rawMaterialList);
        OutputManager.getInstance().errorMassage(
                "*********梨预处理完成*********",
                "*********梨預處理完成*********",
                "*********Pear preprocessing completed*********");
        return rawMaterialList;
    }

    @Override
    public void produce(int count, String produceManner) {
        OutputManager.getInstance().errorMassage(
                "**********正在对梨进行加工*********",
                "**********正在對梨進行加工*********",
                "**********Pears are being processed*********");
        PearProducer pearProducer = new PearProducer(produceManner);
        pearProducer.produce();
        OutputManager.getInstance().errorMassage(
                "共生产" + count + "个梨罐头",
                "共生產" + count + "個梨罐頭",
                "Totally produced" + count + "pear can!");
    }
}
