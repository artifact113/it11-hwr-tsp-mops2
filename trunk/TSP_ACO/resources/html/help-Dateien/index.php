/* <syntaxhighlight lang="javascript"><nowiki> */

var charinsert = {
  "Standard":[
    ["Ä", "ä", "Ö", "ö", "ß", "Ü", "ü"],
    [ ["„","“"], "’", ["‚","‘"], ["“","”"], ["‘","’"], ["«","»"], ["‹","›"], ["»","«"], ["›","‹"], ["–","","","Bisstrich/Gedankenstrich"] ],
    [ "+", ["−","","","mathematisches Minus"], ["·","","","mathematisches Mal"], "×", "÷", "≈", "≠", "±", "≤", "≥", "²", "³", "½", "†", "#", "*",
     "‰", "§", "€", "¢", "£", "¥", "$", "¿", "¡", "∞", "‣", "•", ["〈","〉"], "…", "→", "↔"],
    [ ["&nbsp;","","","Geschütztes Leerzeichen"], ["[[","]]","","Wikilink"], "|", ["{{","}}","","Vorlageneinbindung"], ["~~~~","","","Signatur"] ],
    [["°","","","Grad"], ["′","","","Bogenminute"], ["″","","","Bogensekunde"]]
  ],

  "WikiSyntax":[
    [ [ "[[Kategorie:","]]" ], [ "[[Datei:","]]" ], [ "{{SEITENTITEL:","}}" ], [ "{{SORTIERUNG:","}}" ], [ "#WEITERLEITUNG [[","]]" ] ],
    [ "{{Begriffsklärung}}", "{{Begriffsklärungshinweis}}", [ "{{Dieser Artikel|","}}" ] ],
    [ [ "{{Normdaten|TYP=","|GND=|LCCN=|NDL=|VIAF=}}","", "{{Normdaten|…}}", "{{Normdaten|…}}" ],
      [ "{{Personendaten\n|NAME=","\n|ALTERNATIVNAMEN=\n|KURZBESCHREIBUNG=\n|GEBURTSDATUM=\n|GEBURTSORT=\n|STERBEDATUM=\n|STERBEORT=\n}}","", "{{Personendaten|…}}", "{{Personendaten|…}}" ] ],
    [ [ "<ref>","<\/ref>" ], [ "<ref name=\"","\"><\/ref>" ], [ "<ref name=\"", "\" \/>" ], "<references \/>" ],
    [ [ "<nowiki>","<\/nowiki>" ], [ "<code>","<\/code>" ], [ "<syntaxhighlight lang=\"","\"><\/syntaxhighlight>" ],
     [ "<noinclude>","<\/noinclude>" ], [ "<includeonly>","<\/includeonly>" ], [ "<onlyinclude>","<\/onlyinclude>" ], [ "<math>","<\/math>" ] ],
    [ [ "== "," ==\n", "", "Überschrift Ebene 2" ], [ "=== "," ===\n", "", "Überschrift Ebene 3" ] ]
  ],
  "IPA-Lautschrift":[
    { "class":"IPA" },
    ["p", "t̪", "t", "ʈ", "c", "k", "q", "ʡ", "ʔ"],
    ["b", "d̪", "d", "ɖ", "ɟ", "ɡ", "ɢ"],
    ["ɓ", "ɗ", "ʄ", "ɠ", "ʛ"],
    ["t͡s", "t͡ʃ", "t͡ɕ", "d͡z", "d͡ʒ", "d͡ʑ"],
    ["ɸ", "f", "θ", "s", "ʃ", "ʅ", "ʆ", "ʂ", "ɕ", "ç", "ɧ", "x", "χ", "ħ", "ʜ", "h"],
    ["β", "v", "ʍ", "ð", "z", "ʒ", "ʓ", "ʐ", "ʑ", "ʝ", "ɣ", "ʁ", "ʕ", "ʖ", "ʢ", "ɦ"],
    ["ɬ", "ɮ"],
    ["m", "m̩", "ɱ", "ɱ̩", "ɱ̍", "n̪", "n̪̍", "n", "n̩", "ɳ", "ɳ̩", "ɲ", "ɲ̩", "ŋ", "ŋ̍", "ŋ̩", "ɴ", "ɴ̩"],
    ["ʙ", "ʙ̩", "r", "r̩", "ʀ", "ʀ̩"],
    ["ɾ", "ɽ", "ɿ", "ɺ"],
    ["l̪", "l̪̩", "l", "l̩", "ɫ", "ɫ̩", "ɭ", "ɭ̩", "ʎ", "ʎ̩", "ʟ", "ʟ̩"],
    ["w", "ɥ", "ʋ", "ɹ", "ɻ", "j", "ɰ"],
    ["ʘ", "ǂ", "ǀ", "ǃ", "ǁ"],
    ["ʰ", "ʱ", "ʷ", "ʸ", "ʲ", "ʳ", "ⁿ", "ˡ", "ʴ", "ʵ", "ˢ", "ˣ", "ˠ", "ʶ", "ˤ", "ˁ", "ˀ", "ʼ"],
    ["i", "i̯", "ĩ", "y", "y̯", "ỹ", "ɪ", "ɪ̯", "ɪ̃", "ʏ", "ʏ̯", "ʏ̃", "ɨ", "ɨ̯", "ɨ̃", "ʉ", "ʉ̯", "ʉ̃", "ɯ", "ɯ̯", "ɯ̃", "u", "u̯", "ũ", "ʊ", "ʊ̯", "ʊ̃"],
    ["e", "e̯", "ẽ", "ø", "ø̯", "ø̃", "ɘ", "ɘ̯", "ɘ̃", "ɵ", "ɵ̯", "ɵ̃", "ɤ", "ɤ̯", "ɤ̃", "o", "o̯", "õ"],
    ["ɛ", "ɛ̯", "ɛ̃", "œ", "œ̯", "œ̃", "ɜ", "ɜ̯", "ɜ̃", "ə", "ə̯", "ə̃", "ɞ", "ɞ̯", "ɞ̃", "ʌ", "ʌ̯", "ʌ̃", "ɔ", "ɔ̯", "ɔ̃"],
    ["æ", "æ̯", "æ̃", "ɶ", "ɶ̯", "ɶ̃", "a", "a̯", "ã", "ɐ", "ɐ̯", "ɐ̃", "ɑ", "ɑ̯", "ɑ̃", "ɒ", "ɒ̯", "ɒ̃"],
    ["ˈ", "ˌ", "ː", "ˑ", "˘", ".", "‿", "|", "‖"]
  ],
  "Lateinisch":[
    ["Á", "á", "Ć", "ć", "É", "é", "Í", "í", "Ó", "ó", "Ś", "ś", "Ú", "ú", "Ý", "ý", "Ǿ", "ǿ"],
    ["À", "à", "È", "è", "Ì", "ì", "Ò", "ò", "Ù", "ù"],
    ["Â", "â", "Ĉ", "ĉ", "Ê", "ê", "Ĝ", "ĝ", "Ĥ", "ĥ", "Î", "î", "Ĵ", "ĵ", "Ô", "ô", "ŝ", "Ŝ", "Û", "û"],
    ["Ä", "ä", "Ë", "ë", "Ï", "ï", "Ö", "ö", "Ü", "ü", "ÿ"],
    ["Ã", "ã", "Ñ", "ñ", "Õ", "õ"],
    ["Å", "å"],
    ["Ç", "ç"],
    ["Č", "č", "Š", "š", "ŭ"],
    ["Ł", "ł"],
    ["Ő", "ő", "Ű", "ű"],
    ["Ø", "ø"],
    ["Ā", "ā", "Ē", "ē", "Ī", "ī", "Ō", "ō", "Ū", "ū", "Ȳ", "ȳ"],
    ["Ă", "ă", "Ĕ", "ĕ", "Ğ", "ğ", "Ĭ", "ĭ", "Ŏ", "ŏ", "Ŭ", "ŭ", "Y̆", "y̆"],
    ["ß"],
    ["Æ", "æ", "Œ", "œ"],
    ["Ð", "ð", "Þ", "þ", "|"]
  ],
  "AHD-Lautschrift":[
    { "class":"Unicode" },
    ["ā", "ă", "ä", "â", "ē", "ĕ", "ī", "ĭ", "î", "ō", "ŏ", "ô", "ŭ", ["o͞o","","","food"], ["o͝o","","","foot"]]
  ],
  "Altenglisch":[
    { "lang":"ang" },
    ["Ā", "ā", "Æ", "æ", "Ǣ", "ǣ", "Ǽ", "ǽ", "Ċ", "ċ", "Ð", "ð", "Ē", "ē", "Ġ", "ġ", "Ī", "ī", "Ō", "ō", "Ū", "ū", "Ƿ", "ƿ", "Ȳ", "ȳ", "Þ", "þ", "Ȝ", "ȝ"]
  ],
  "Altgriechisch":[
    { "lang":"grc", "class":"polytonic" },
    ["Α", "α", "Ά", "ά", "Β", "β", "Γ", "γ", "Δ", "δ", "Ε", "ε", "Έ", "έ", "Ζ", "ζ", "Η", "η", "Ή", "ή", "Θ", "θ", "Ι",
     "ι", "Ί", "ί", "Ϊ", "ϊ", "ΐ", "Κ", "κ", "Λ", "λ", "Μ", "μ", "Ν", "ν", "Ξ", "ξ", "Ο", "ο", "", "Ό", "ό", "Π", "π",
     "Ρ", "ρ", "Σ", "σ", "ς", "Τ", "τ", "Υ", "υ", "Ϋ", "ϋ", "Ύ", "ύ", "ΰ", "Φ", "φ", "Χ", "χ", "Ψ", "ψ", "Ω", "ω", "Ώ",
     "ώ", ";", "·", "ἀ", "ἁ", "ὰ", "ᾶ", "ἂ", "ἃ", "ἄ", "ἅ", "ἆ", "ἇ", "ᾳ", "ᾀ", "ᾁ", "ᾴ", "ᾲ", "ᾷ", "ᾄ", "ᾅ", "ᾂ", "ᾃ",
     "ᾆ", "ᾇ", "ἐ", "ἑ", "ὲ", "ἔ", "ἕ", "ἒ", "ἓ", "ἠ", "ἡ", "ὴ", "ῆ", "ἤ", "ἢ", "ἣ", "ἥ", "ἦ", "ἧ", "ῃ", "ῄ", "ῂ", "ῇ",
     "ᾐ", "ᾑ", "ᾔ", "ᾒ", "ᾕ", "ᾓ", "ᾖ", "ᾗ", "ἰ", "ἱ", "ὶ", "ῖ", "ἴ", "ἲ", "ἵ", "ἳ", "ἶ", "ἷ", "ὸ", "ὀ", "ὁ", "ὄ", "ὅ",
     "ὂ", "ὃ", "ῤ", "ῥ", "ὐ", "ὑ", "ὺ", "ῦ", "ὔ", "ὕ", "ὒ", "ὓ", "ὖ", "ὗ", "ὠ", "ὡ", "ὼ", "ῶ", "ὤ", "ὢ", "ὥ", "ὣ", "ὦ",
     "ὧ", "ῳ", "ῴ", "ῲ", "ῷ", "ᾠ", "ᾡ", "ᾤ", "ᾢ", "ᾥ", "ᾣ", "ᾦ", "ᾧ", "`", "᾿", "῾", "῍", "῎", "῏", "῟", "῞", "῝", "῍",
     "῎", "Ϝ", "ϝ", "Ϙ", "ϙ", "Ϡ", "ϡ"]
  ],
  "Arabisch":[
    { "direction":"rtl", "lang":"ar", "class":"spanAr", "font-size":"1.25em" },
    ["؛", "؟", "ء", "آ", "أ", "ؤ", "إ", "ئ", "ا", "ب", "ة", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز", "س", "ش", "ص",
     "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "م", "ن", "ه", "و", "ى", "ي", "،"],
    ["پ", "چ", "ژ", "گ", "ڭ"]
  ],
  "DMG-Umschrift":[
    ["ʾ", "ʿ", "Ā", "ā", "Č", "č", "Ḍ", "ḍ", "Ḏ", "ḏ", "Ǧ", "ǧ", "Ġ", "ġ", "Ḥ", "ḥ", "Ḫ", "ḫ", "Ī", "ī", "ḷ", "ŋ", "Ṣ",
     "ṣ", "Š", "š", "Ṭ", "ṭ", "Ṯ", "ṯ", "Ū", "ū", "Ẓ", "ẓ", "Ẕ", "ẕ", "Ž", "ž"]
  ],
  "Esperanto":[
    { "lang":"eo" },
    ["Ĉ", "ĉ", "Ĝ", "ĝ", "Ĥ", "ĥ", "Ĵ", "ĵ", "Ŝ", "ŝ", "Ŭ", "ŭ"]
  ],
  "Estnisch":[
    { "lang":"et" },
    ["Č", "č", "Š", "š", "Ž", "ž", "Õ", "õ", "Ä", "ä", "Ö", "ö", "Ü", "ü"]
  ],
  "Französisch":[
    { "lang":"fr" },
    ["À", "à", "Â", "â", "Ç", "ç", "É", "é", "È", "è", "Ê", "ê", "Ë", "ë", "Î", "î", "Ï", "ï", "Ô", "ô", "Œ", "œ", "Ù", "ù", "Û", "û", "Ü", "ü", "Ÿ", "ÿ"]
  ],
  "Galicisch":[
    { "lang":"gl" },
    ["Á", "á", "À", "à", "Â", "â", "Ä", "ä", "É", "é", "È", "è", "Ê", "ê", "Ë", "ë", "Ì", "ì", "Î", "î", "Ï", "ï", "Ó", "ó", "Ò",
     "ò", "Ô", "ô", "Ö", "ö", "Ù", "ù", "Û", "û", "Ẁ", "ẁ", "Ŵ", "ŵ", "Ẅ", "ẅ", "Ý", "ý", "Ỳ", "ỳ", "Ŷ", "ŷ", "Ÿ", "ÿ"]
  ],
  "Griechisch":[
    { "lang":"hl" },
    ["Α", "Ά", "Β", "Γ", "Δ", "Ε", "Έ", "Ζ", "Η", "Ή", "Θ", "Ι", "Ί", "Κ", "Λ", "Μ", "Ν", "Ξ", "Ο", "Ό", "Π", "Ρ", "Σ", "Τ", "Υ", "Ύ", "Φ", "Χ", "Ψ", "Ω", "Ώ"],
    ["α", "ά", "β", "γ", "δ", "ε", "έ", "ζ", "η", "ή", "θ", "ι", "ί", "κ", "λ", "μ", "ν", "ξ", "ο", "ό", "π", "ρ", "σ", "ς", "τ", "υ", "ύ", "φ", "χ", "ψ", "ω", "ώ"]
  ],
  "Hawaiisch":[
    { "lang":"haw", "font-family":"'Arial Unicode MS','Lucida Sans Unicode','MS Mincho',Arial,sans-serif;" },
    ["Ā", "ā", "Ē", "ē", "Ī", "ī", "Ō", "ō", "Ū", "ū", "ʻ"]
  ],
  "Isländisch":[
    { "lang":"is" },
    ["Á", "á", "Ð", "ð", "É", "é", "Í", "í", "Ó", "ó", "Ú", "ú", "Ý", "ý", "Þ", "þ", "Æ", "æ", "Ö", "ö", "ǫ"]
  ],
  "Italienisch":[
    { "lang":"it" },
    ["Á", "á", "À", "à", "É", "é", "È", "è", "Í", "í", "Ì", "ì", "Ó", "ó", "Ò", "ò", "Ú", "ú", "Ù", "ù"]
  ],
  "Jiddisch":[
    { "lang":"yi", "direction":"rtl" },
    ["", "א", "אַ", "אָ", "ב", "בֿ", "ג", "ד", "ה", "ו", "וּ", "װ", "ױ", "ז", "זש", "ח", "ט", "י", "יִ", "ײ", "ײַ", "כ", "ך", "כּ",
     "ל", ["","ל"], "מ", "ם", "נ", "ן", "ס", "ע","ע", "פ", "פּ", "פֿ", "ף", "צ", "ץ", "ק", "ר", "ש", "שׂ", "תּ", "ת", "׳", "״", "־", ""]
  ],
  "Kroatisch/Serbisch/Bosnisch":[
    { "lang":"hbs" },
    ["Č", "č", "Ć", "ć", "Dž", "dž", "Đ", "đ", "Š", "š", "Ž", "ž"]
  ],
  "Kyrillisch":[
    ["А", "Ә", "Б", "В", "Г", "Ґ", "Ѓ", "Ғ", "Д", "Ђ", "Е", "Є", "Ё", "Ж", "З", "Ѕ", "И", "І", "Ї", "İ", "Й", "Ӣ", "Ј", "К",
     "Ќ", "Қ", "Л", "Љ", "М", "Н", "Њ", "Ң", "О", "Ө", "П", "Р", "С", "Т", "Ћ", "У", "Ў", "Ӯ", "Ұ", "Ү", "Ф", "Х", "Ҳ", "Һ",
     "Ц", "Ч", "Ҷ", "Џ", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"],
    ["а", "ә", "б", "в", "г", "ґ", "ѓ", "ғ", "д", "ђ", "е", "є", "ё", "ж", "з", "ѕ", "и", "і", "ї", "й", "ӣ", "ј", "к", "ќ", "қ",
     "л", "љ", "м", "н", "њ", "ң", "о", "ө", "п", "р", "с", "т", "ћ", "у", "ў", "ӯ", "ұ", "ү", "ф", "х", "ҳ", "һ", "ц", "ч", "ҷ",
     "џ", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"]
  ],
  "Lettisch":[
    { "lang":"lv" },
    ["Ā", "Č", "Ē", "Ģ", "Ī", "Ķ", "Ļ", "Ņ", "Š", "Ū", "Ž"],
    ["ā", "č", "ē", "ģ", "ī", "ķ", "ļ", "ņ", "š", "ū", "ž"]
  ],
  "Litauisch":[
    { "lang":"lt" },
    ["Ą", "Č", "Ę", "Ė", "Į", "Š", "Ų", "Ū", "Ž"],
    ["ą", "č", "ę", "ė", "į", "š", "ų", "ū", "ž"]
  ],
  "Maltesisch":[
    { "lang":"mt" },
    ["Ċ", "ċ", "Ġ", "ġ", "Ħ", "ħ", "Ż", "ż"]
  ],
  "Pinyin":[
    ["Á", "á", "À", "à", "Ǎ", "ǎ", "Ā", "ā", "É", "é", "È", "è", "Ě", "ě", "Ē", "ē", "Í", "í", "Ì", "ì", "Ǐ", "ǐ", "Ī", "ī", "Ó",
     "ó", "Ò", "ò", "Ǒ", "ǒ", "Ō", "ō", "Ú", "ú", "Ù", "ù", "Ü", "ü", "Ǔ", "ǔ", "Ū", "ū", "Ǘ", "ǘ", "Ǜ", "ǜ", "Ǚ", "ǚ", "Ǖ", "ǖ"]
  ],
  "Polnisch":[
    { "lang":"pl" },
    ["ą", "Ą", "ć", "Ć", "ę", "Ę", "ł", "Ł", "ń", "Ń", "ó", "Ó", "ś", "Ś", "ź", "Ź", "ż", "Ż"]
  ],
  "Portugiesisch":[
    { "lang":"pt" },
    ["Á", "á", "À", "à", "Â", "â", "Ã", "ã", "Ç", "ç", "É", "é", "Ê", "ê", "Í", "í", "Ó", "ó", "Ô", "ô", "Õ", "õ", "Ú", "ú", "Ü", "ü"]
  ],
  "Romanisch":[
    { "lang":"roa" },
    ["Ā", "ā", "Ē", "ē", "Ī", "ī", "Ō", "ō", "Ū", "ū"]
  ],
  "Rumänisch":[
    { "lang":"ro" },
    ["Ă", "ă", "Â", "â", "Î", "î", "Ș", "ș", "Ț", "ț"]
  ],
  "Skandinavisch":[
    ["À", "à", "É", "é", "Å", "å", "Æ", "æ", "Ä", "ä", "Ø", "ø", "Ö", "ö"]
  ],
  "Slowakisch":[
    { "lang":"sk" },
    ["Á", "á", "Č", "č", "Ď", "ď", "É", "é", "Í", "í", "Ľ", "ľ", "Ň", "ň", "Ó", "ó", "Ô", "ô", "Ŕ", "ŕ", "Š", "š", "Ť", "ť", "Ú", "ú", "Ý", "ý", "Ž", "ž"]
  ],
  "Sorbisch":[
    { "lang":"wen" },
    ["Č", "č", "Ć", "ć", "ě", "Ł", "ł", "ń", "ó", "ř", "ŕ", "Š", "š", "Ś", "ś", "Ž", "ž", "Ź", "ź"]
  ],
  "Spanisch":[
    { "lang":"es" },
    ["Á", "á", "É", "é", "Í", "í", "Ñ", "ñ", "Ó", "ó", "Ú", "ú", "Ü", "ü", "¡", "¿"]
  ],
  "Tschechisch":[
    { "lang":"cz" },
    ["Á", "á", "Č", "č", "Ď", "ď", "É", "é", "Ě", "ě", "Í", "í", "Ň", "ň", "Ó", "ó", "Ř", "ř", "Š", "š", "Ť", "ť", "Ú", "ú", "Ů", "ů", "Ý", "ý", "Ž", "ž"]
  ],
  "Türkisch":[
    { "lang":"tr" },
    ["Â", "Ə", "Ç", "Ğ", "Gʻ", "Î", "İ", "Ñ", "Ň", "Oʻ", "Ş", "Û", "Ý", "Ž"],
    ["â", "ə", "ç", "ğ", "gʻ", "î", "ı", "ñ", "ň", "oʻ", "ş", "û", "ý", "ž"]
  ],
  "Ungarisch":[
    { "lang":"hu" },
    ["á", "é", "í", "Ő", "ö", "ó", "ő", "Ű", "ú", "ü", "ű"]
  ],
  "Vietnamesisch":[
    { "lang":"vi" },
    ["À", "à", "Ả", "ả", "Á", "á", "Ạ", "ạ", "Ã", "ã", "Ă", "ă", "Ằ", "ằ", "Ẳ", "ẳ", "Ẵ", "ẵ", "Ắ", "ắ", "Ặ",
     "ặ", "Â", "â", "Ầ", "ầ", "Ẩ", "ẩ", "Ẫ", "ẫ", "Ấ", "ấ", "Ậ", "ậ", "Đ", "đ", "È", "è", "Ẻ", "ẻ", "Ẽ", "ẽ",
     "É", "é", "Ẹ", "ẹ", "Ê", "ê", "Ề", "ề", "Ể", "ể", "Ễ", "ễ", "Ế", "ế", "Ệ", "ệ", "Ỉ", "ỉ", "Ĩ", "ĩ", "Í",
     "í", "Ị", "ị", "Ì", "ì", "Ỏ", "ỏ", "Ó", "ó", "Ọ", "ọ", "Ò", "ò", "Õ", "õ", "Ô", "ô", "Ồ", "ồ", "Ổ", "ổ",
     "Ỗ", "ỗ", "Ố", "ố", "Ộ", "ộ", "Ơ", "ơ", "Ờ", "ờ", "Ở", "ở", "Ỡ", "ỡ", "Ớ", "ớ", "Ợ", "ợ", "Ù", "ù", "Ủ",
     "ủ", "Ũ", "ũ", "Ú", "ú", "Ụ", "ụ", "Ư", "ư", "Ừ", "ừ", "Ử", "ử", "Ữ", "ữ", "Ứ", "ứ", "Ự", "ự", "Ỳ", "ỳ",
     "Ỷ", "ỷ", "Ỹ", "ỹ", "Ỵ", "ỵ", "Ý", "ý"]
  ]
};

addOnloadHook(function() {
addOnloadHook(function() {
  var box;
  function loadCommonsTools() {
    function selectSubset() {
      var pp = box.getElementsByTagName("p");
      for (var i=0; i<pp.length; ++i) {
        pp[i].style["display"] = "none";
      }
      //show/create current subset
      var id = sel.options[sel.selectedIndex].value;
      var p = document.getElementById(id);
      if(!p) {
        p = document.createElement('p');
        p.setAttribute("id", id);
        p.setAttribute("class", "mwEdittoolsLanguage");
        createTokens(p, charinsert[id.substr("mwEdittools--".length)]);
        box.appendChild(p);
      }
      p.style["display"] = "inline";
    }
    
    function createTokens(paragraph, outerArr) {
      var buttons;
      
      function insertInner(elem) {
        var a = false, ins;
        switch(typeof(elem)) {
          case("string"): {
            ins = function() {
              insertTags(elem, "", "");
              return false;
            };
            a = document.createElement("a");
            a.setAttribute("title", elem);
            a.appendChild(document.createTextNode(elem));
          } break;
          case("array"):
          case("object"): {
            ins = function() {
              insertTags(elem[0], elem[1] || "", elem[2] || "");
              return false;
            };
            a = document.createElement("a");
            a.setAttribute("title", elem[3] || (elem[0] + (elem[2] || "…") + (elem[1] || "")));
            a.appendChild(document.createTextNode(elem[4] || (elem[0] + (elem[1] || ""))));
          }; break;
        }
        if(a !== false) {
          a.onclick = ins;
          a.setAttribute("href", "#");
          a.setAttribute("class", "mwEdittoolsButton");
          buttons.appendChild(document.createTextNode(" "));
          buttons.appendChild(a);
        }
      }
      
      function insertOuter(innerArr) {
        switch(typeof(innerArr)) {
          case("object"):
          case("array"):
            var obj = false;
            if(typeof(innerArr["class"]) === "string") {
              obj = true;
              paragraph.setAttribute("class", innerArr["class"]);
            }
            if(typeof(innerArr["lang"]) === "string") {
              obj = true;
              paragraph.setAttribute("lang", innerArr["lang"]);
            }
            if(typeof(innerArr["direction"]) === "string") {
              obj = true;
              paragraph.style.direction = innerArr["direction"];
            }
            if(typeof(innerArr["font-family"]) === "string") {
              obj = true;
              paragraph.style.fontFamily = innerArr["font-family"];
            }
            if(typeof(innerArr["font-size"]) === "string") {
              obj = true;
              paragraph.style.fontSize = innerArr["font-size"];
            }
            if(obj) {
              return false;
            }
            buttons = document.createElement("span");
            buttons.setAttribute("class", "mwEdittoolsButtons");
            for(var i = 0; i<innerArr.length; ++i) {
              insertInner(innerArr[i]);
            }
            paragraph.appendChild(buttons);
            return true;
          default:
            return false;
        }
      }
      
      if(outerArr.length == 0) { return; }
      for(var i=0; i<outerArr.length-1; ++i) {
        if(insertOuter(outerArr[i])) {
          var s = document.createElement("span");
          s.appendChild(document.createTextNode("\xA0\xA0•\xA0 "));
          s.style["font-weight"] = "bold";
          paragraph.appendChild(s);
        }
      }
      insertOuter(outerArr[outerArr.length-1]);
    }
    
    //create drop-down select
    var sel = document.createElement("select");
    for(var id in charinsert) {
      var op = document.createElement("option");
      op.setAttribute("value", "mwEdittools--" + id);
      op.appendChild(document.createTextNode(id));
      sel.appendChild(op);
    }
    sel.setAttribute("title", "Zeichensatz auswählen");
    sel.onchange = sel.onkeyup = selectSubset;
    box.appendChild(sel);
    box.appendChild(document.createTextNode(" "));
    selectSubset();
    return false;
  }
  
  //get div#mw-editTools
  box = document.getElementById('mw-editTools');
  if(!box) { return; }
  
  box = box.appendChild(document.createElement("div"));
  box.setAttribute("id", "specialchars");
  
  loadCommonsTools();
})
});
/* </nowiki></syntaxhighlight> */