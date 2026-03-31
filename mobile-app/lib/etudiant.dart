class Etudiant {
  final int? id;
  final String cin;
  final String nom;
  final String dateNaissance;

  Etudiant({
    this.id,
    required this.cin,
    required this.nom,
    required this.dateNaissance,
  });

  factory Etudiant.fromJson(Map<String, dynamic> json) {
    return Etudiant(
      id: json['id'],
      cin: json['cin'] ?? '',
      nom: json['nom'] ?? '',
      dateNaissance: json['dateNaissance'] ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'cin': cin,
      'nom': nom,
      'dateNaissance': dateNaissance,
    };
  }
}
