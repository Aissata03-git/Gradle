Feature: UserController API Tests

  Background:
    * url baseUrl = 'http://localhost:8080/users'

  # Test pour récupérer tous les utilisateurs
  Scenario: Get All Users
    Given url baseUrl
    When method GET
    Then status 200
    And match each response == { id: '#number', name: '#string', adresse: '#string', password: '#string' }

  # Test pour créer un nouvel utilisateur
  Scenario: Create new User
    Given url baseUrl
    And request { id: null, name: 'Aissata', adresse : 'dakar', password: '234567890'}
    When method POST
    Then status 200
    And match response == { id: '#notnull', name: 'Aissata', adresse : 'dakar', password: '234567890'}

  # Test pour récupérer un utilisateur existant par son ID
  Scenario: Get User by ID
    Given url baseUrl + '/1'
    When method GET
    Then status 200
    And match response == { id: 1, name: '#string', adresse: '#string', password: '#string' }

  # Test pour récupérer un utilisateur qui n'existe pas (erreur 404)
  Scenario: Get Non-Existing User by ID
    Given url baseUrl + '/999'
    When method GET
    Then status 404
    And match response == { message: 'user not found' }

  # Test pour mettre à jour un utilisateur existant
  Scenario: Update User
    Given url baseUrl + '/1'
    And request { id: 1, name: 'UpdatedName', adresse: 'UpdatedAddress', password: 'newpassword123' }
    When method PUT
    Then status 200
    And match response == { id: 1, name: 'UpdatedName', adresse: 'UpdatedAddress', password: 'newpassword123' }

  # Test pour supprimer un utilisateur existant
  Scenario: Delete User by ID
    Given url baseUrl + '/1'
    When method DELETE
    Then status 200
    And match response == { message: 'User deleted successfully' }

  # Test pour tenter de supprimer un utilisateur qui n'existe pas (erreur 404)
  Scenario: Delete Non-Existing User by ID
    Given url baseUrl + '/999'
    When method DELETE
    Then status 404
    And match response == { message: 'user not found' }
