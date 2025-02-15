const API_URL = "http://localhost:8085/LogementRendezVous_Etudiant_war_exploded/api/";// 🔄 Récupérer et afficher la liste des logements
async function fetchLogements() {
    try {
        const response = await fetch(`${API_URL}logements`);
        if (!response.ok) throw new Error("Erreur lors de la récupération des logements");

        const logements = await response.json();
        let rows = "";

        logements.forEach(logement => {
            rows += `
                <tr>
                    <td>${logement.reference}</td>
                    <td>${logement.adresse}</td>
                    <td>${logement.delegation}</td>
                    <td>${logement.gouvernorat}</td>
                    <td>${logement.type}</td>
                    <td>${logement.prix} DT</td>
                    <td><button onclick="deleteLogement('${logement.reference}')">Supprimer</button></td>
                </tr>
            `;
        });

        document.getElementById("logementsTable").innerHTML = rows;
    } catch (error) {
        console.error("Erreur:", error);
    }
}

// ➕ Ajouter un nouveau logement
document.getElementById("logementForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const logement = {
        adresse: document.getElementById("adresse").value,
        delegation: document.getElementById("delegation").value,
        gouvernorat: document.getElementById("gouvernorat").value,
        type: document.getElementById("type").value,
        description: document.getElementById("description").value,
        prix: parseFloat(document.getElementById("prix").value)
    };

    console.log("Sending logement:", logement); // Debugging statement

    try {
        const response = await fetch(`${API_URL}logements`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(logement)
        });

        console.log("Response status:", response.status); // Debugging statement

        if (!response.ok) throw new Error("Erreur lors de l'ajout du logement");

        fetchLogements();
    } catch (error) {
        console.error("Erreur:", error);
    }
});

// ❌ Supprimer un logement
async function deleteLogement(reference) {
    try {
        const response = await fetch(`${API_URL}logements/${reference}`, { method: "DELETE" });
        if (!response.ok) throw new Error("Erreur lors de la suppression du logement");

        fetchLogements();
    } catch (error) {
        console.error("Erreur:", error);
    }
}

// Charger les logements au démarrage
fetchLogements();
