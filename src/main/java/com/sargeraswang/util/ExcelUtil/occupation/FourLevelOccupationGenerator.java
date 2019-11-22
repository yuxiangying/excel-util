package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.OccupationDTO;
import com.sargeraswang.util.ExcelUtil.occupation.dto.Option;
import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public abstract class FourLevelOccupationGenerator extends AbstractOccupationGenerator {


    public FourLevelOccupationGenerator(int start, int end) {
        super(start, end);
    }

    @Override
    protected OccupationDTO getOccupationDTO(Sheet sheet) {
        SortMap<String, SortMap<String, SortMap<String, List<String>>>> bigMap = occupationOfFourLevel(sheet);
        return toOccupationDTO(bigMap);
    }

    protected abstract SortMap<String, SortMap<String, SortMap<String, List<String>>>> occupationOfFourLevel(Sheet sheet);

    private OccupationDTO toOccupationDTO(SortMap<String, SortMap<String, SortMap<String, List<String>>>> bigMap) {

        OccupationDTO first = new OccupationDTO();
        first.setName("1");
        List<Option> options = new ArrayList<>();
        first.setOptions(options);
        for (String firstKey : bigMap.keyList()) {
            Option firstOption = new Option();

            String[] firstValueAndLabel = firstKey.split("#");
            firstOption.setValue(firstValueAndLabel[0]);
            firstOption.setLabel(firstValueAndLabel[1]);
            OccupationDTO second = new OccupationDTO();
            firstOption.setChildren(second);

            second.setName("2");
            List<Option> secondOptions = new ArrayList<>();
            second.setOptions(secondOptions);


            options.add(firstOption);
            SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(firstKey);

            for (String secondKey : secondMap.keyList()) {

                Option secondOption = new Option();
                String[] secondValueAndLabel = secondKey.split("#");
                secondOption.setValue(secondValueAndLabel[0]);
                secondOption.setLabel(secondValueAndLabel[1]);

                OccupationDTO third = new OccupationDTO();
                secondOption.setChildren(third);

                third.setName("3");
                List<Option> thirdOptions = new ArrayList<>();
                third.setOptions(thirdOptions);

                secondOptions.add(secondOption);


                SortMap<String, List<String>> thirdMap = secondMap.get(secondKey);
                for (String thirdKey : thirdMap.keyList()) {
                    Option thirdOption = new Option();

                    String[] thirdValueAndLabel = thirdKey.split("#");
                    thirdOption.setValue(thirdValueAndLabel[0]);
                    thirdOption.setLabel(thirdValueAndLabel[1]);

                    OccupationDTO four = new OccupationDTO();
                    thirdOption.setChildren(four);

                    four.setName("4");
                    List<Option> fourOptions = new ArrayList<>();
                    four.setOptions(fourOptions);

                    thirdOptions.add(thirdOption);

                    List<String> fourList = thirdMap.get(thirdKey);
                    for (String fourKey : fourList) {
                        Option fourOption = new Option();
                        String[] fourValueAndLabel = fourKey.split("#");
                        fourOption.setValue(fourValueAndLabel[0]);
                        fourOption.setLabel(fourValueAndLabel[1]);
                        fourOptions.add(fourOption);
                    }

                }
            }
        }
        return first;
    }
}
