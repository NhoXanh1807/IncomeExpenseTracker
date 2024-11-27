package com.CodeElevate.ExpenseTracker.services.income;


import com.CodeElevate.ExpenseTracker.dto.IncomeDTO;
import com.CodeElevate.ExpenseTracker.entity.Income;
import com.CodeElevate.ExpenseTracker.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository ;
    public Income postIncome(IncomeDTO incomeDTO){
        return saveOrUpdateExpense(new Income(), incomeDTO);
    }
    private Income saveOrUpdateExpense(Income income, IncomeDTO incomeDTO){
        income.setTitle(income.getTitle());
        income.setDate(income.getDate());
        income.setAmount(income.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        return incomeRepository.save(income);
    }
    public Income updateIncome(Long id, IncomeDTO incomeDTO){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            return saveOrUpdateExpense(optionalIncome.get(), incomeDTO);
        } else{
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }
    public List<IncomeDTO> getAllIncomes(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate))
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }
    public IncomeDTO getIncomeById(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            return optionalIncome.get().getIncomeDto();
        } else{
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }
    public void deleteIncome(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            incomeRepository.deleteById(id);
        } else{
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }
}
