// Skript von [[Benutzer:✓]], das protokollabsolute Links auf Wikimedia-Projekte in -relative ändert
mw.loader.using( [ 'mediawiki.util' ], function() { $( function() {
    // zuverlässig mit beiden Protokollen verhaltensgleich funktionierende Domains
    var reg = new RegExp("^https?\\:\\/\\/(?:" + [
        // (download|.*?mobile|.*?m|mail) funktionieren nicht oder unterschiedlich
        "(?!download|[^/#?]*?mobile|[^/#?]*?m|mail)[^/#?]*?\\.?wik(?:ipedia|tionary|ibooks|iquote|iversity|isource|inews|imediafoundation)\\.org",
        // (dumps|download|stats|noc|ganglia|[^/]*?planet|mail) funktionieren nicht oder unterschiedlich
        // ticket und bugzilla leiten eh nur auf https weiter, donate auf http://wikimediafoundation.org
        // das wäre nur lang und umständlich: "(lists|upload|techblog|blog|wikitech|svn|commons|incubator|.+?\\.labs|nyc|species|advisory|ar|bd|br|co|dk|et|fi|il|mk|mx|nl|no|nc|pa\\.us|pl|pt|rs|ru|se|tr|ua|uk|ve|board|boardgovcom|noboard\\.chapters|auditcom|chair|chapcom|checkuser|steward|collab|exec|grants|internal|movementroles|office|searchcom|spcom|otrs-wiki|quality|meta|outreach|volunteer|strategy|usability|survey|wikimania[^/#?]*?)\\.wikimedia",
        "(?!secure|dumps|download|stats|ganglia|nagios|[^/#?]*?planet|mail|ticket|bugzilla|donate)[^/#?]*?\\.?wikimedia\\.org",
        "[^/#?]*?\\.?mediawiki\\.org",
        // wiki.toolserver leitet eh nur auf https weiter
        "[^/#?]*?\\.?toolserver\\.org",
        "[^/#?]*?\\.?wikimedia\\.ch",
        "[^/#?]*?\\.?wikimedia\\.at",
        "[^/#?]*?\\.?wikimedia\\.de"
    ].join("|") + ")(?:[/#?]|$)");
    // urls mit relative=no am Ende unverändert lassen
    var regUnchanged = /[&?]relative=no$/;
    mw.util.$content.find("a").each( function(index, link) {
        var href = link.getAttribute('href');
        if (!href || href.substring(0, 4) !== "http") {
            return;
        }
        if (href.search(reg) === 0) {
            var indexUnchanged = href.search( regUnchanged );
            if( indexUnchanged !== -1 ) {
                link.setAttribute('href', href.substring( 0, indexUnchanged ) ); //Schlüsselwort entfernen
            } else {
                link.setAttribute('href', href.substr(href.substr(4,1) === "s" ? 6 : 5 ));
            }
            return;
        }
        var parts = href.match(/^https\:\/\/secure\.wikimedia\.org\/(.+?)\/(.+?)\/(.*)/);
        if (!parts || parts.length < 3) {
            return;
        }
        var projekt = parts[1];
        var version = parts[2];
        var page = parts[3];
        if (projekt !== "wikipedia") {
            if (projekt.substring(0, 6) === "skins-") { // funktioniert eh nicht mehr, die "Korrektur" aber ist tödlich
                return;
            }
            href = "//" + version + "." + projekt + ".org";
        } else {
            switch (version) {
                case "foundation": href = "//wikimediafoundation.org"; break;
                case "sources":    href = "//wikisource.org"; break;
                case "mediawiki":  href = "//www.mediawiki.org"; break;
                case "species":
                case "meta":
                case "commons":
                case "incubator":  projekt = "wikimedia"; //no break
                default: href = "//" + version + "." + projekt + ".org";
            }
        }
        link.setAttribute('href', href + "/" + page);
    });
})});