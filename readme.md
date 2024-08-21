# Personal Finance Application

Welcome to the Personal Finance Application! This project helps you manage and track your personal finances efficiently. The application is built with Spring Boot and utilizes a PostgreSQL database for data storage.

## Features

- **Expense Tracking**: Record and categorize your expenses.
- **Income Management**: Track various sources of income.
- **Budgeting**: Set monthly budgets and monitor your spending against them.
- **Currency Conversion**: Convert between currencies using the integrated API.
- **Analytics**: View reports and visualizations of your financial data.

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: PostgreSQL
- **API Integration**: Currency conversion using [AnyAPI.io](https://anyapi.io/)
- **Logging**: SLF4J, Logback
- **Deployment**: Google Cloud's GKE

## Getting Started

### Prerequisites

- Java 17+
- PostgreSQL
- Maven
- IntelliJ IDEA (recommended)

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Anujkrsh/Personal-finance-app.git
    cd Personal-finance-app
    ```

2. **Set up the PostgreSQL database:**

   Create a PostgreSQL database named `personal_finance` and update the `application.properties` file with your database credentials.

3. **Configure the Currency Conversion API Key:**

   Store your `apiKey` securely using IntelliJ's Secret Manager. Configure it during deployment.

4. **Build the project:**

    ```bash
    mvn clean install
    ```

5. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

### Usage

1. **Access the application:**
   Open `http://localhost:8080` in your browser.

2. **Explore the features:**
    - Add and manage your expenses and income.
    - Set up budgets and track your spending.
    - Use the currency conversion feature for accurate financial data.

## API Integration

The application uses the [AnyAPI.io](https://anyapi.io/) service for currency conversion. The request URL is:
https://anyapi.io/api/v1/exchange/convert 

Query parameters:
- `to`: Target currency
- `base`: Base currency
- `amount`: Amount to convert
- `apiKey`: Your API key (stored securely)

Response fields:
- `base`: Base currency
- `to`: Target currency
- `amount`: Requested amount
- `converted`: Converted amount
- `rate`: Conversion rate
- `lastUpdate`: Timestamp of the last rate update

## Contributing

Feel free to fork this repository, create a branch, and submit a pull request for any improvements or features.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## API_Key

Please Do Take care for APi Key as the api key were injected externally and is not store in the project

## Contact

For any queries or suggestions, please contact [Anuj Kumar Sharma](mailto:your-email@example.com).
