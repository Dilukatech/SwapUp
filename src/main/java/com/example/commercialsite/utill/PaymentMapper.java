package com.example.commercialsite.utill;

import com.example.commercialsite.dto.response.PaymentTableResponseDTO;
import com.example.commercialsite.entity.PaymentTable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    List<PaymentTableResponseDTO> entityListToDTOList(List<PaymentTable> paymentTableList);
}
