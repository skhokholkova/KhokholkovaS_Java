package org.lab.controller;

import org.lab.model.Human;
import org.lab.model.Sex;

import java.util.Random;

public class HumanCreator implements Creator<Human> {
    private static final String[] MALE_FIRST_NAMES = {
            "Олександр", "Андрій", "Максим", "Дмитро", "Іван", "Богдан", "Сергій", "Артем"};
    private static final String[] FEMALE_FIRST_NAMES = {
            "Олена", "Марія", "Анна", "Софія", "Наталія", "Ірина", "Катерина", "Вікторія"};
    private static final String[] LAST_NAMES = {
            "Шевченко", "Коваленко", "Бондаренко", "Ткаченко", "Кравченко", "Мельник", "Лисенко", "Савченко"};
    private static final String[] MALE_MIDDLE_NAMES = {
            "Олександрович", "Андрійович", "Максимович", "Дмитрович", "Іванович", "Богданович", "Сергійович", "Артемович"};
    private static final String[] FEMALE_MIDDLE_NAMES = {
            "Олександрівна", "Андріївна", "Максимівна", "Дмитрівна", "Іванівна", "Богданівна", "Сергіївна", "Артемівна"};

    private final Random random = new Random(42);

    @Override
    public Human create() {
        Sex sex = random.nextBoolean() ? Sex.MALE : Sex.FEMALE;
        String[] firstNames = (sex == Sex.MALE) ? MALE_FIRST_NAMES : FEMALE_FIRST_NAMES;
        String[] middleNames = (sex == Sex.MALE) ? MALE_MIDDLE_NAMES : FEMALE_MIDDLE_NAMES;
        return new Human(pick(firstNames), pick(LAST_NAMES), pick(middleNames), sex);
    }

    private String pick(String[] values) {
        return values[random.nextInt(values.length)];
    }
}