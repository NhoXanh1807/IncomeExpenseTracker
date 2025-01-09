const typeFilter = document.getElementById('type-filter');
const categoryFilter = document.getElementById('category-filter');
const dateFrom = document.getElementById('date-from');
const dateTo = document.getElementById('date-to');
const tableBody = document.getElementById('table-body');
const allButton = document.getElementById('all-button');
const titleSearchInput = document.querySelector('input[type="text"]');

typeFilter.addEventListener('change', filterTable);
categoryFilter.addEventListener('change', filterTable);
dateFrom.addEventListener('change', filterTable);
dateTo.addEventListener('change', filterTable);
titleSearchInput.addEventListener('input', filterTable);

function parseDate(dateString) {
  const [day, month, year] = dateString.split('/').map(Number);
  return new Date(year, month - 1, day);
}

function filterTable() {
  const type = typeFilter.value;
  const category = categoryFilter.value;
  const fromDate = dateFrom.value ? new Date(dateFrom.value) : null;
  const toDate = dateTo.value ? new Date(dateTo.value) : null;
  const titleSearch = titleSearchInput.value.toLowerCase();
  allButton.style.backgroundColor="transparent";
  allButton.style.border="2px solid #ffffff";
  const rows = Array.from(tableBody.rows);
  rows.forEach(row => {
    const rowType = row.cells[0].textContent.toLowerCase();
    const rowCategory = row.cells[4].textContent.toLowerCase();
    const rowDate = parseDate(row.cells[3].textContent);
    const rowTitle = row.cells[1].textContent.toLowerCase();

    let show = true;

    if (titleSearch && !rowTitle.includes(titleSearch)) {
      show = false;
    }

    if (type && rowType !== type) {
      show = false;
    }

    if (category && !rowCategory.includes(category)) {
      show = false;
    }

    if (fromDate && rowDate < fromDate) {
      show = false;
    }

    if (toDate && rowDate > toDate) {
      show = false;
    }

    row.style.display = show ? '' : 'none';
  });
}

allButton.addEventListener('click', () => {
  typeFilter.value = '';
  categoryFilter.value = '';
  dateFrom.value = '';
  dateTo.value = '';
  allButton.style.backgroundColor="#1990FE";
  allButton.style.border="none";

  const rows = Array.from(tableBody.rows);
  rows.forEach(row => {
    row.style.display = '';
  });
});

const reportRows = document.querySelectorAll('tr.report');

// Add click event listener to each report row
reportRows.forEach(reportRow => {
  reportRow.addEventListener('click', () => {
    // Find the next sibling row (assumed to be the description row)
    const descRow = reportRow.nextElementSibling;
    
    if (descRow && descRow.classList.contains('desc')) {
      // Toggle the visibility of the description row
      descRow.style.display = descRow.style.display === 'none' ? '' : 'none';
    }
  });
});

// Initially hide all description rows
const descRows = document.querySelectorAll('tr.desc');
descRows.forEach(descRow => {
  descRow.style.display = 'none';
});

document.addEventListener('DOMContentLoaded', () => {
  const storedImageUrl = localStorage.getItem('profileImageUrl');
  if (storedImageUrl) {
      const sidebarImg = document.querySelector('.sidebar-user .modify-image img');
      sidebarImg.src = storedImageUrl;
  }
});