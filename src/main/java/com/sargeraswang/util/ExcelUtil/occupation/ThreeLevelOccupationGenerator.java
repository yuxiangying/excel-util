package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.OccupationDTO;
import com.sargeraswang.util.ExcelUtil.occupation.dto.Option;
import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class ThreeLevelOccupationGenerator extends AbstractOccupationGenerator {


    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public ThreeLevelOccupationGenerator(int start, int end) {
        super(start, end);
    }

    @Override
    protected OccupationDTO getOccupationDTO(Sheet sheet) {
        SortMap<String, SortMap<String, List<String>>> bigMap = occupationOfThreeLevel(sheet);
        return toOccupationDTO(bigMap);
    }

    protected abstract SortMap<String, SortMap<String, List<String>>> occupationOfThreeLevel(Sheet sheet);

    private OccupationDTO toOccupationDTO(SortMap<String, SortMap<String, List<String>>> bigMap) {

        List<String> keyList = bigMap.keyList();

        OccupationDTO first = new OccupationDTO();
        first.setName("1");
        List<Option> firstOptions = new ArrayList<>();
        first.setOptions(firstOptions);
        for (String firstKey : keyList) {
            Option firstOption = new Option();
            logger.debug("firstKey==>{}", firstKey);

            firstOption.setValue(firstKey.split("#")[0]);
            firstOption.setLabel(firstKey.split("#")[1]);
            OccupationDTO second = new OccupationDTO();
            firstOption.setChildren(second);

            second.setName("2");
            List<Option> secondOptions = new ArrayList<>();
            second.setOptions(secondOptions);

            firstOptions.add(firstOption);

            SortMap<String, List<String>> midMap = bigMap.get(firstKey);
            for (String secondKey : midMap.keyList()) {
                logger.debug("secondKey==>{}", secondKey);

                Option secondOption = new Option();
                secondOption.setValue(secondKey.split("#")[0]);
                secondOption.setLabel(secondKey.split("#")[1]);

                OccupationDTO third = new OccupationDTO();
                secondOption.setChildren(third);

                third.setName("3");
                List<Option> thirdOptions = new ArrayList<>();
                third.setOptions(thirdOptions);

                secondOptions.add(secondOption);

                List<String> thirdList = midMap.get(secondKey);
                for (String thirdKey : thirdList) {
                    logger.debug("thirdKey==>{}", thirdKey);
                    Option thirdOption = new Option();
                    thirdOption.setValue(thirdKey.split("#")[0]);
                    thirdOption.setLabel(thirdKey.split("#")[1]);
                    thirdOptions.add(thirdOption);
                }
            }
        }
        return first;
    }
}
