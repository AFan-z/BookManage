package com.demo.service.Impl;

import com.demo.entity.OperationAllEntity;
import com.demo.entity.TableView.OperationInfo;
import com.demo.mapper.OperationMapper;
import com.demo.service.OperationService;
import com.demo.utils.MapperFactory;
import org.yaml.snakeyaml.error.YAMLException;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;

import java.util.ArrayList;
import java.util.List;

public class OperationServiceImpl implements OperationService {
    private OperationMapper operationMapper = MapperFactory.getOperationMapperInstance();

    @Override
    public List<OperationInfo> getOperationList() {
        List<OperationInfo> operationInfos = new ArrayList<>();

        try {
            List<OperationAllEntity> entities = operationMapper.select();
            for (OperationAllEntity entity : entities){
                OperationInfo operationInfo = new OperationInfo(entity);
                operationInfos.add(operationInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        }catch (YAMLException e2) {
            handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return operationInfos;
    }

    @Override
    public List<OperationInfo> getOperationListByJobNum(String jobNum) {
        List<OperationInfo> operationInfos = new ArrayList<>();

        try {
            List<OperationAllEntity> entities = operationMapper.select(jobNum);
            for (OperationAllEntity entity : entities){
                OperationInfo operationInfo = new OperationInfo(entity);
                operationInfos.add(operationInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        }catch (YAMLException e2) {
            handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return operationInfos;
    }

    @Override
    public boolean addOperationInfo() {
        return false;
    }
}
