import 'package:flutter/material.dart';
import 'package:wissal_project/api_service.dart';
import 'package:wissal_project/etudiant.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Student List',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true,
      ),
      home: const EtudiantListScreen(),
    );
  }
}

class EtudiantListScreen extends StatefulWidget {
  const EtudiantListScreen({super.key});

  @override
  State<EtudiantListScreen> createState() => _EtudiantListScreenState();
}

class _EtudiantListScreenState extends State<EtudiantListScreen> {
  final ApiService _apiService = ApiService();
  late Future<List<Etudiant>> _etudiantsFuture;

  @override
  void initState() {
    super.initState();
    _etudiantsFuture = _apiService.getEtudiants();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Student List'),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
      ),
      body: FutureBuilder<List<Etudiant>>(
        future: _etudiantsFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          } else if (snapshot.hasError) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Icon(
                    Icons.error_outline,
                    size: 48,
                    color: Colors.red,
                  ),
                  const SizedBox(height: 16),
                  Text(
                    'Error: ${snapshot.error}',
                    textAlign: TextAlign.center,
                    style: const TextStyle(color: Colors.red),
                  ),
                  const SizedBox(height: 16),
                  ElevatedButton(
                    onPressed: () {
                      setState(() {
                        _etudiantsFuture = _apiService.getEtudiants();
                      });
                    },
                    child: const Text('Retry'),
                  ),
                ],
              ),
            );
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(
              child: Text('No students found'),
            );
          }

          final etudiants = snapshot.data!;
          return RefreshIndicator(
            onRefresh: () async {
              setState(() {
                _etudiantsFuture = _apiService.getEtudiants();
              });
            },
            child: ListView.builder(
              itemCount: etudiants.length,
              itemBuilder: (context, index) {
                final etudiant = etudiants[index];
                return Card(
                  margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                  elevation: 4,
                  child: Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          etudiant.nom,
                          style: const TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const SizedBox(height: 8),
                        Row(
                          children: [
                            const Icon(Icons.badge, size: 16, color: Colors.grey),
                            const SizedBox(width: 4),
                            Text(
                              'CIN: ${etudiant.cin}',
                              style: const TextStyle(color: Colors.grey),
                            ),
                          ],
                        ),
                        const SizedBox(height: 4),
                        Row(
                          children: [
                            const Icon(Icons.cake, size: 16, color: Colors.grey),
                            const SizedBox(width: 4),
                            Text(
                              'Birth Date: ${etudiant.dateNaissance}',
                              style: const TextStyle(color: Colors.grey),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                );
              },
            ),
          );
        },
      ),
    );
  }
}
