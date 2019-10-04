package org.Third.Chapter.CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamTest {

	static class Person {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}

	public static List<Person> makeList() {
		List<Person> personList = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setAge(10);
		p1.setName("zlx");
		personList.add(p1);

		p1 = new Person();
		p1.setAge(12);
		p1.setName("jiaduo");
		personList.add(p1);

		p1 = new Person();
		p1.setAge(5);
		p1.setName("ruoran");
		personList.add(p1);
		return personList;
	}

	public static void useStream(List<Person> personList) {

		List<String> nameList = personList.stream().filter(person -> person.getAge() >= 10)// 1.过滤大于等于10的
				.map(person -> person.getName())// 2.使用map映射元素
				.collect(Collectors.toList());// 3.收集映射后元素

		nameList.stream().forEach(name -> System.out.println(name));
	}

	public static void noStream(List<Person> personList) {

		List<String> nameList = new ArrayList<>();

		for (Person person : personList) {
			if (person.age >= 10) {
				nameList.add(person.getName());
			}
		}

		for (String name : nameList) {
			System.out.println(name);
		}

	}

	public static void main(String[] args) {

		List<Person> personList = makeList();

		noStream(personList);

	}

}
