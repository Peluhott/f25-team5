document.addEventListener("DOMContentLoaded", function () {
    const API_KEY = "9b066c1c5fe54101969a8914d0b1186a";
    const input = document.getElementById("location-input");
    const suggestionsBox = document.getElementById("location-suggestions");

    if (!input || !suggestionsBox) {
        console.warn("Location autocomplete: input or suggestions box not found.");
        return;
    }

    function clearSuggestions() {
        suggestionsBox.innerHTML = "";
        suggestionsBox.style.display = "none";
    }

    function createSuggestionItem(text) {
        const div = document.createElement("div");
        div.className = "autocomplete-item";
        div.textContent = text;

        div.addEventListener("click", function () {
            input.value = text;
            clearSuggestions();
        });

        return div;
    }

    async function fetchSuggestions(query) {
        if (!query || query.length < 2) {
            clearSuggestions();
            return;
        }

        const url = new URL("https://api.geoapify.com/v1/geocode/autocomplete");
        url.searchParams.set("text", query);
        url.searchParams.set("apiKey", API_KEY);
       

        try {
            const response = await fetch(url.toString());
            if (!response.ok) {
                console.error("Geoapify error:", response.status, response.statusText);
                clearSuggestions();
                return;
            }

            const data = await response.json();
            const results = data.features || [];

            suggestionsBox.innerHTML = "";

            results.forEach(feature => {
                const props = feature.properties || {};
                const city = props.city || props.town || props.village || "";
                const state = props.state || props.region || "";

                if (!city && !state) return;

                const display = state ? `${city}, ${state}` : city;
                const item = createSuggestionItem(display);
                suggestionsBox.appendChild(item);
            });

            if (suggestionsBox.children.length > 0) {
                suggestionsBox.style.display = "block";
            } else {
                clearSuggestions();
            }
        } catch (err) {
            console.error("Geoapify autocomplete error:", err);
            clearSuggestions();
        }
    }

    
    input.addEventListener("input", function () {
        const value = input.value.trim();
        fetchSuggestions(value);
    });

    
    input.addEventListener("blur", function () {
        setTimeout(clearSuggestions, 200);
    });
});
