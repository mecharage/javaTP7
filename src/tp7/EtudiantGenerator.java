package tp7;

import java.util.Random;

public class EtudiantGenerator {

	static Etudiant generate(Random rng) {
		Etudiant e = new Etudiant();

		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append((char)('A' + rng.nextInt('z' - 'a' + 1)));
		
		for (int i = 0, l = 5 + rng.nextInt(5); i < l; ++i)
			nameBuilder.append((char)('a' + rng.nextInt('z' - 'a' + 1)));

		e.nom = nameBuilder.toString();
		e.age = 19 + rng.nextInt(28 - 19);
		
		return e;
	}
}
