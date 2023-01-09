package kg.mega.saloon.service;

import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.requests.SaveMasterRequest;

public interface MasterService extends BaseService<MasterDto>{

    MasterDto create(SaveMasterRequest master);


}
