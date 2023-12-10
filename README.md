# JFXPackager

![Imagem](https://i.imgur.com/UPBDHJn.png)
![Imagem](https://i.imgur.com/xIAFNpu.png)

O sistema JPackage com Interface Gráfica, construído com JavaFX, permite que você acesse a funcionalidades básicas e robustas do `jpackage` de uma maneira fácil e intuitiva. Aqui estão as principais funções que você pode realizar com este sistema:

1. **App Directory**: Defina o diretório de entrada que contém os arquivos do aplicativo que você deseja empacotar.

2. **App Name**: Especifique o nome do seu aplicativo. Este nome será usado para nomear o pacote gerado.

3. **Jar**: Indique o arquivo JAR principal do seu aplicativo. Este é o JAR que contém a classe principal do seu aplicativo.

4. **Main Class**: Defina a classe principal do seu aplicativo. Esta é a classe que contém o método `main()`.

5. **Icon**: Personalize o ícone do seu aplicativo. Você pode fornecer o caminho para o arquivo de imagem que deseja usar como ícone.

6. **Destination**: Especifique o diretório de destino onde o pacote gerado será salvo.

7. **App Version**: Defina a versão do seu aplicativo. Esta versão será incluída nas informações do pacote.

8. **Vendor**: Indique o fornecedor do aplicativo. Esta informação será incluída nas informações do pacote.

9. **Type**: Selecione o tipo de pacote que você deseja criar. As opções incluem os formatos "app-image", "exe", "msi", "rpm", "deb", "pkg", "dmg".

10. **Create Shortcut**: Crie um atalho para o seu aplicativo no Windows ou em sistemas baseados em Linux. Basta marcar a caixa de seleção correspondente na interface gráfica.

11. **Add Description**: Deixe uma breve descrição sobre o seu aplicativo; 

Além disso, o sistema suporta a criação de pacotes nos formatos que o `jpackage` suporta. Com o JFXPackager, empacotar seu aplicativo Java fica mais fácil e flexível.

---

_Observação_: O sistema JFXPackage depende da estabilidade do empacotador jpackage para funcionar corretamente.
