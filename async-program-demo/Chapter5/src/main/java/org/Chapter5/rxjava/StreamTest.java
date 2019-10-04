package org.Chapter5.rxjava;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Flowable;

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

	public static void main(String[] args) {

		// 1.创建person列表
		List<Person> personList = makeList();

		// 2.执行过滤与输出
		Flowable.fromArray(personList.toArray(new Person[0]))// 2.1转换列表为Flowable流对象
				.filter(person -> person.getAge() >= 10)// 2.2过滤
				.map(person -> person.getName())// 2.3.映射转换
				.subscribe(System.out::println);// 2.4订阅输出

		Flowable<Person> source = Flowable.fromArray(personList.toArray(new Person[0]));
		Flowable<Person> filterSource = source.filter(person -> person.getAge() >= 10);
		Flowable<String> nameSource = filterSource.map(person -> person.getName());
		nameSource.subscribe(System.out::println);

	}
}
