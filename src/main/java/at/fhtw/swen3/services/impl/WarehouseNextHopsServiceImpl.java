package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.services.WarehouseNextHopsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Slf4j
public class WarehouseNextHopsServiceImpl implements WarehouseNextHopsService {

    @Autowired
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;
}
