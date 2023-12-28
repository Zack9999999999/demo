(g => { var h, a, k, p = "The Google Maps JavaScript API", c = "google", l = "importLibrary", q = "__ib__", m = document, b = window; b = b[c] || (b[c] = {}); var d = b.maps || (b.maps = {}), r = new Set, e = new URLSearchParams, u = () => h || (h = new Promise(async (f, n) => { await (a = m.createElement("script")); e.set("libraries", [...r] + ""); for (k in g) e.set(k.replace(/[A-Z]/g, t => "_" + t[0].toLowerCase()), g[k]); e.set("callback", c + ".maps." + q); a.src = `https://maps.${c}apis.com/maps/api/js?` + e; d[q] = f; a.onerror = () => h = n(Error(p + " could not load.")); a.nonce = m.querySelector("script[nonce]")?.nonce || ""; m.head.append(a) })); d[l] ? console.warn(p + " only loads once. Ignoring:", g) : d[l] = (f, ...n) => r.add(f) && u().then(() => d[l](f, ...n)) })({
    key: "AIzaSyBjHlN8i2-4cw4QDuaPgyctq7OX1n6c6ls",
});
// Initialize and add the map
let map;
let marker;

async function initMap() {
    const position = { lat: 24.957, lng: 121.225 };

    const { Map } = await google.maps.importLibrary("maps");
    const { Marker } = await google.maps.importLibrary("marker");

    map = new Map(document.getElementById("map"), {
        zoom: 17,
        center: position,
        mapId: "DEMO_MAP_ID",
        gestureHandling: "none",
        disableDefaultUI: true
    });

    marker = new Marker({
        map,
        position: position,
        title: "user"
    });
}
initMap();