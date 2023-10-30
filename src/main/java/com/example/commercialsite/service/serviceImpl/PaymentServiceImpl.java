package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.response.PaymentTableResponseDTO;
import com.example.commercialsite.entity.PaymentTable;
import com.example.commercialsite.repository.PaymentTableRepository;
import com.example.commercialsite.service.PaymentService;
import com.example.commercialsite.utill.PaymentMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentTableRepository paymentTableRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<PaymentTableResponseDTO> getAllPaymentsByIsPayment(boolean isPayment) {

        List<PaymentTable> paymentTableList=paymentTableRepository.findByIsPayment(isPayment);
        List<PaymentTableResponseDTO> paymentTableResponseDTOList = paymentMapper.entityListToDTOList(paymentTableList);
        return paymentTableResponseDTOList;
    }
}
