package com.CodeElevate.ExpenseTracker.services.search;

import com.CodeElevate.ExpenseTracker.dto.SearchDTO;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Map<String, Object>> search(SearchDTO searchDTO);
}
