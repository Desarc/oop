package oving5.person;

import java.util.ArrayList;

public class Person {
	
	private String name;
	private Gender gender;
	
	private Person father;
	private Person mother;
	
	private ArrayList<Person> children;
	
	public Person() {
		children = new ArrayList<Person>();
	}
	
	public Person(char gender) {
		this();
		if (gender == 'm') {
			this.gender = Gender.MALE;
		}
		else if (gender == 'f') {
			this.gender = Gender.FEMALE;
		}
	}
	
	public boolean isMale() {
		return (gender == Gender.MALE);
	}
	
	public boolean isFemale() {
		return (gender == Gender.FEMALE);
	}
	
	public Person(String name, char gender) {
		this(gender);
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		for (int i = 0;i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
				return;
			}
		}
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		if (this.father != father && this.father != null) {
			this.father.removeChild(this);
		}
		if (father == null) {
			this.father = father;
		}
		else if (father.getGender() == Gender.MALE) {
			this.father = father;
			father.addChild(this);
		}
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		if (this.mother != mother && this.mother != null) {
			this.mother.removeChild(this);
		}
		if (mother == null) {
			this.mother = mother;
		}
		else if (mother.getGender() == Gender.FEMALE) {
			this.mother = mother;
			mother.addChild(this);
		}
	}
	
	public void addChild(Person child) {
		if (!children.contains(child)) {
			children.add(child);
			if (gender == Gender.FEMALE) {
				child.setMother(this);
			}
			else if (gender == Gender.MALE) {
				child.setFather(this);
			}
		}
	}
	
	public int getChildCount() {
		return children.size();
	}
	
	public Person getChild(int i) {
		if (i < getChildCount() && i >= 0) {
			return children.get(i);
		}
		return null;
	}
	
	public void removeChild(Person child) {
		if (children.contains(child)) {
			children.remove(child);
			if (gender == Gender.FEMALE) {
				child.setMother(null);
			}
			else if (gender == Gender.MALE) {
				child.setFather(null);
			}
		}
	}
	
	public int indexOfChild(Person child) {
		if (children.contains(child)) {
			return children.indexOf(child);
		}
		return -1;
	}
	
	public boolean containsChild(Person child) {
		return children.contains(child);
	}
	
	public boolean isAncestorOf(Person person) {
		if (person == null) {
			return false;
		}
		else if (this == person.getFather() || this == person.getMother()) {
			return true;
		}
		return (isAncestorOf(person.getFather()) || isAncestorOf(person.getMother()));
	}
	
	
}
