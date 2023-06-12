package com.Parallel.ServiceOrchestration.Worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.Parallel.ServiceOrchestration.Model.Employer;

public class Employee {

	public static void main(String[] args) {

		List<Employer> list = new ArrayList<>();
		list.add(new Employer("Hari", 26));
		list.add(new Employer("Kannan", 30));
		list.add(new Employer("Naveen", 25));
		list.add(new Employer("Ashok", 24));
		list.add(new Employer("Gayathri", 23));
		list.add(new Employer("Rajesh", 29));
		list.add(new Employer("Murali", 35));
		list.add(new Employer("Akshay", 28));

		Collections.sort(list, new Comparator<Employer>() {

			@Override
			public int compare(Employer t, Employer t1) {
				return t.getAge() - t1.getAge();
			}
		});
		System.out.println(list);
		List<Employer> Sort = list.stream().limit(4).collect(Collectors.toList());
		System.out.println(Sort);

	}

}
