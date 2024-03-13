package step.learning.dal.dao;

import step.learning.dal.dto.PropItem;

import java.util.UUID;

public class PropDao {

    public PropItem[] GetPropItems(){
        PropItem PropItem1 = new PropItem(
                UUID.randomUUID(),
                "УМБ XO Power Bank 20000mAh PR183 Light",
                "336305793.webp",
                "3000",
                "2999"
                );

        PropItem PropItem2 = new PropItem(
                UUID.randomUUID(),
                "Фітнес-браслет Xiaomi Smart Band 8 Black",
                "367323203.webp",
                "5000",
                "4000"
        );
        PropItem PropItem3 = new PropItem(
                UUID.randomUUID(),
                "Смарт-годинник Huawei Watch GT3 42",
                "368324204.png",
                "6000",
                "5000"
        );

        return new PropItem[]{
                PropItem1, PropItem2, PropItem3
        };
    }
}
