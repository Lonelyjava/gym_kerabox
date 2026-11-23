package com.gym.kerabox.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gym.kerabox.entity.Employee;
import com.gym.kerabox.reposistory.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	public int uploadExcel(MultipartFile file) throws Exception {

		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("Please upload an Excel file");
		}

		List<Employee> employees = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

			Sheet sheet = workbook.getSheetAt(0);

			// Skip header row
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);

				if (row == null) {
					continue;
				}

				String email = getString(row, 3);

				// Skip empty rows
				if (email.trim().isEmpty()) {
					continue;
				}

				Employee employee = new Employee();

				employee.setFirstName(getString(row, 1));
				employee.setLastName(getString(row, 2));
				employee.setEmail(email);
				employee.setPhoneNumber(getString(row, 4));
				employee.setDepartment(getString(row, 5));
				employee.setDesignation(getString(row, 6));

				String salary = getString(row, 7);

				if (!salary.trim().isEmpty()) {
//                    employee.setSalary(new BigDecimal(salary));
					employee.setSalary(getSalary(row.getCell(7)));
				}

				employee.setJoiningDate(getDate(row.getCell(8)));

				employee.setCity(getString(row, 9));

				employees.add(employee);
			}
		}

		employeeRepository.saveAll(employees);

		return employees.size();
	}

	private BigDecimal getSalary(Cell cell) {

		if (cell == null) {
			return null;
		}

		// If Excel cell contains a numeric value
		if (cell.getCellType() == CellType.NUMERIC) {
			return BigDecimal.valueOf(cell.getNumericCellValue());
		}

		// If Excel cell contains text like ₹50,000.00
		String salary = new DataFormatter().formatCellValue(cell).trim();

		if (salary.isEmpty()) {
			return null;
		}

		// Remove rupee symbol, commas and other unwanted characters
		salary = salary.replaceAll("[^0-9.\\-]", "");

		if (salary.isEmpty()) {
			return null;
		}

		return new BigDecimal(salary);
	}

	private String getString(Row row, int column) {

		Cell cell = row.getCell(column);

		if (cell == null) {
			return "";
		}

		DataFormatter formatter = new DataFormatter();

		return formatter.formatCellValue(cell).trim();
	}

	private LocalDate getDate(Cell cell) {

		if (cell == null) {
			return null;
		}

		if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {

			return cell.getDateCellValue().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
		}

		String value = new DataFormatter().formatCellValue(cell).trim();

		if (value.isEmpty()) {
			return null;
		}

		return LocalDate.parse(value);
	}
}
