package com.iris.core.mapper

import com.iris.core.model.Writer
import com.iris.entrypoint.dto.WriterDto

class WriterConverter {
    companion object {
        fun writerDtoToWriter(writerDto: WriterDto) =
            Writer(writerDto.id, writerDto.nome, writerDto.nationality)

        fun writerToWriterDto(writer: Writer) =
            WriterDto(writer.id, writer.nome, writer.nationality)
    }
}