# Guía para ejecutar proyecto Spring Boot localmente

## Prerrequisitos
- Java JDK 17 o superior instalado
- Maven 3.9.0 o superior instalado
- IDE de tu preferencia (recomendado: IntelliJ IDEA, Eclipse, VS Code)
- Git (opcional, para clonar repositorios)

## Paso 1: Verificar instalaciones

1. Verifica la versión de Java:
```bash
java -version
```

2. Verifica la versión de Maven:
```bash
mvn -version
```

## Paso 2: Obtener el proyecto

### Opción A: Clonar desde repositorio
```bash
git clone <URL-del-repositorio>
cd <nombre-del-proyecto>
```

### Opción B: Proyecto existente
Simplemente navega hasta el directorio del proyecto:
```bash
cd ruta/al/proyecto
```

## Paso 3: Configuración del proyecto

1. Revisa el archivo `application.properties` en:
```
src/main/resources/application.properties
```

2. Configura las variables de entorno necesarias:
- Base de datos
- Credenciales
- Puertos
- Otros parámetros específicos

## Paso 4: Instalar dependencias

Ejecuta el siguiente comando en la raíz del proyecto:
```bash
mvn clean install
```

## Paso 5: Ejecutar el proyecto

### Opción A: Usando Maven
```bash
mvn spring-boot:run
```

### Opción B: Usando el JAR
```bash
java -jar target/<nombre-del-proyecto>.jar
```

### Opción C: Desde el IDE
1. Abre el proyecto en tu IDE
2. Busca la clase principal (anotada con @SpringBootApplication)
3. Click derecho -> Run As -> Spring Boot App

## Paso 6: Verificar la ejecución

1. Espera a que el proyecto inicie completamente
2. Verifica los logs en busca de errores
3. Por defecto, accede a:
   - http://localhost:8089 (puerto predeterminado)
   - http://localhost:8089/swagger-ui.html (si Swagger está configurado)

## Solución de problemas comunes

### Puerto en uso
```bash
lsof -i :8089                  # Linux/Mac
```

### Error de conexión a base de datos
1. Verifica que la base de datos esté corriendo
2. Confirma las credenciales en application.properties
3. Asegúrate de que el esquema existe

### Error de Java/Maven
1. Verifica las variables de entorno JAVA_HOME y MAVEN_HOME
2. Confirma que las versiones son compatibles con el proyecto

## Comandos útiles

### Limpiar y reconstruir
```bash
mvn clean install -DskipTests
```

### Ejecutar tests
```bash
mvn test
```

### Ver dependencias
```bash
mvn dependency:tree
```


## Configuración para Despliegue en Azure

1. se crear el pipeline de Azure DevOps
2. se agregar el respositorio git
3. se seleciona el archivo /desploy/azure-pipelines.yml
4. se configurar la variable para pipeline ejemplo: ACR_REPOSITORY_NAME, API_PREFIX, APP_PORT
4. se verificar los permisos para docker y kubernetes
5. crear grupo de variables en azure Library donde se va configurar la variable secreta o generales que va necesitar los microservicios
6. se agregar el permiso de grupo de variables para que pueda ejecutar el pipeline
7. Configuracion de azure Release donde se crea por cada microservicio un release y se configurar para que despliegue el microservicio.
8. proceso de publicacion.
9. revisar el portal azure el Servicios API Management y verificar que la ip de load balancer este configurada correctamente.