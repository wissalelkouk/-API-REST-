import 'dart:convert';
import 'package:http/http.dart' as http;
import 'etudiant.dart';

class ApiService {
  static const String baseUrl = 'http://localhost:8080'; // For web testing
  // Use 'http://10.0.2.2:8080' for Android emulator
  
  Future<List<Etudiant>> getEtudiants() async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/api/etudiants'),
        headers: {
          'Content-Type': 'application/json',
        },
      );

      if (response.statusCode == 200) {
        List<dynamic> jsonResponse = json.decode(response.body);
        return jsonResponse.map((etudiant) => Etudiant.fromJson(etudiant)).toList();
      } else {
        throw Exception('Failed to load students: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Error connecting to API: $e');
    }
  }
}
