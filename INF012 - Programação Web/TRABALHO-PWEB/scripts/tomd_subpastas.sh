# Exporta os arquivos de cada subpasta para um arquivo Markdown.
# Uso: tomd_subpastas <extensão>

tomd_subpastas() {
    local ext="$1"

    if [ -z "$ext" ]; then
        echo "Uso: tomd_subpastas <extensão>"
        return 1
    fi

    local output_dir="$HOME/Downloads"

    for dir in */; do

        local nome_pasta="${dir%/}"
        local arquivo_saida="$output_dir/${nome_pasta}-${ext}.md"

        echo "Exportando $nome_pasta..."

        # Localiza os arquivos da extensão informada, ignorando a pasta target.
        find "$dir" -type d -name target -prune -o -type f -name "*.$ext" -print0 |
        while IFS= read -r -d '' file; do

            echo "===== Arquivo: $file ====="
            echo
            cat "$file"
            echo

        done > "$arquivo_saida"

        if [ -s "$arquivo_saida" ]; then
            echo "Criado: $arquivo_saida"
        else
            rm -f "$arquivo_saida"
            echo "Nenhum arquivo .$ext encontrado em $nome_pasta"
        fi

    done
}