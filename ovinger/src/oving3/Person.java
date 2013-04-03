package oving3;

import java.util.ArrayList;

import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class Person extends ConsoleProgram {
	String name;
	Person mother;
	Person father;
	ArrayList<Person> children;

	public void run() {
		Person harald = new Person("Harald");
		Person sonja = new Person("Sonja");
		Person haakon = new Person("Haakon", sonja, harald);
		Person mettemarit = new Person("Mette-Marit");
		Person ingrida = new Person("Ingrid Alexandra", mettemarit, haakon);
		Person sigurdm = new Person("Sigurd Magnus", mettemarit, haakon);
		Person martha = new Person("Märtha", sonja, harald);
		Person ari = new Person("Ari");
		Person mauda = new Person("Maud Angelica", martha, ari);
		Person leahi = new Person("Leah Isadora", martha, ari);
		Person emmat = new Person("Emma Talulah", martha, ari);
		println(haakon);
		println(martha);
		
		
	}
	
	public Person() {
		children = new ArrayList<Person>();
	}
	
	public Person(String name, Person mother, Person father) {
		this(name);
		setMother(mother);
		setFather(father);
	}
	
	public Person(String name) {
		this();
		this.name = name;
	}
	
	public boolean isMotherOf(Person person) {
		if (this == person.mother && children.contains(person)) {
			return true;
		}
		return false;
	}
	
	public boolean isFatherOf(Person person) {
		if (this == person.father && children.contains(person)) {
			return true;
		}
		return false;
	}
	
	public boolean isSiblingOf(Person person) {
		if (this != person && mother.isMotherOf(person) && father.isFatherOf(person)
				&& person.father.isFatherOf(this) && person.mother.isMotherOf(this)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s = "";
		s += "Name: "+this.name+"\n";
		s += "Mother: "+this.mother.name+"\n";
		s += "Father: "+this.father.name+"\n";
		s += "Children: ";
		for (int i = 0; i < children.size(); i++) {
			s += children.get(i).name;
			if (i < children.size()-1) {
				s += ", ";
			}
		}
		s += "\n";
		return s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
		mother.addChild(this);
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
		father.addChild(this);
	}
	
	public void addChild(Person child) {
		if (!children.contains(child)) {
			children.add(child);
		}
	}
	
	public void removeChild(Person child) {
		if (!children.contains(child)) {
			children.remove(child);
		}
	}
}
