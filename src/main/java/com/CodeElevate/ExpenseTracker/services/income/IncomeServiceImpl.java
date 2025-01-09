package com.CodeElevate.ExpenseTracker.services.income;

import com.CodeElevate.ExpenseTracker.dto.IncomeDTO;
import com.CodeElevate.ExpenseTracker.entity.Income;
import com.CodeElevate.ExpenseTracker.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDTO incomeDTO) {
        return saveOrUpdateIncome(new Income(), incomeDTO);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO) {
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        return incomeRepository.save(income);
    }

    public Page<IncomeDTO> getAllIncomes(Integer page, String sortType) {
        Sort sort = Sort.by("title");

        switch (sortType.toLowerCase()) {
            case "desc":
                sort = Sort.by(Sort.Direction.DESC, "title");
                break;
            case "asc":
                sort = Sort.by(Sort.Direction.ASC, "title");
                break;
            case "tohigher":
                sort = Sort.by(Sort.Direction.ASC, "amount");
                break;
            case "tolower":
                sort = Sort.by(Sort.Direction.DESC, "amount");
                break;
            default:
                sort = Sort.by(Sort.Direction.ASC, "title");
                break;
        }
        PageRequest pageable = PageRequest.of(page, 10, sort);

        Page<Income> incomePage = incomeRepository.findAll(pageable);

        List<IncomeDTO> incomeDTOs = incomePage.getContent().stream()
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());

        return new PageImpl<>(incomeDTOs, pageable, incomePage.getTotalElements());

    }

    public Income updateIncome(Long id, IncomeDTO incomeDTO) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
        } else {
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }

    public List<IncomeDTO> getAllIncomes() {
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate))
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public IncomeDTO getIncomeById(Long id) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDto();
        } else {
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }

    public void deleteIncome(Long id) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            incomeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }
}