package tp7;

public class Etudiant {
	public String nom;
	public int age;

	@Override
	public String toString() {
		return String.format("%20s  %3d ans", nom, age);
	}
}
