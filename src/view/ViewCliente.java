package view;

import controller.ClienteController;
import controller.PetController;
import model.Animal;
import model.Cliente;
import util.Logger;
import java.util.Scanner;

public class ViewCliente {
    public static void menu(Scanner scanner, PetController petController, ClienteController clienteController) {
        int opcao = 0;
        while (opcao != 3) {
            System.out.println("\n--- Área do Cliente ---");
            System.out.println("1. Cadastrar-se");
            System.out.println("2. Ver pets disponíveis e Adotar");
            System.out.println("3. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Telefone: ");
                String tel = scanner.nextLine();

                clienteController.cadastrar(new Cliente(nome, cpf, tel));
                System.out.println("Cliente cadastrado com sucesso!");

            } else if (opcao == 2) {
                System.out.println("\nPets Disponíveis:");
                for (Animal p : petController.listarDisponiveis()) {
                    System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Som que faz: " + p.emitirSom());
                }

                System.out.print("\nDigite o seu CPF para registrar a adoção: ");
                String cpfDono = scanner.nextLine();

                if (clienteController.buscarPorCpf(cpfDono) == null) {
                    System.out.println("CPF não encontrado. Cadastre-se primeiro.");
                    continue;
                }

                System.out.print("Digite o ID do pet que deseja adotar: ");
                int idPet = scanner.nextInt();
                scanner.nextLine();

                Animal pet = petController.buscarPet(idPet);

                if (pet != null && !pet.isAdotado()) {
                    pet.setCpfDono(cpfDono);
                    petController.atualizar(pet.getId(), pet);
                    Logger.registrar("Adoção realizada! Pet ID " + pet.getId() + " para CPF: " + cpfDono);
                    System.out.println("Parabéns pela adoção! Cuide bem do seu novo amigo.");
                } else {
                    System.out.println("Pet não encontrado ou já adotado.");
                }
            }
        }
    }
}