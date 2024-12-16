package TNT.SE_2024_02_TNT.service;

import TNT.SE_2024_02_TNT.dto.*;
import TNT.SE_2024_02_TNT.entity.*;
import TNT.SE_2024_02_TNT.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ItemService
        (
         val containerRepository:ContainerRepository
        )
{
        fun itemAssignService(request: ItemAssignDtoRequest) {

        }
}
