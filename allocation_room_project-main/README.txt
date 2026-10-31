```
.
├── README.md
├── LICENSE.txt
├── Rapport.docx
├── lib/                     bibliothèques (JUnit)
├── UML/                     diagrammes de conception
├── src/
│   ├── AllocationRoom.java  point d'entrée
│   ├── domain/              classes métier
│   ├── algorithm/           coeur de l'algorithme
│   └── ui/                  menus et lecture au clavier
└── test/
    ├── RunTest.java         test d'intégration (programme complet)
    ├── domain/              tests unitaires de domain
    ├── algorithm/           tests unitaires de algorithm
    └── stress/
        ├── Generator.java   générateur d'instances aléatoires
        └── StressTest.java  comparaison avec une version brute de référence
```