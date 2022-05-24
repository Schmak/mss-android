package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.common.TemplateDto
import com.mss.core.network.v4.model.common.create
import com.mss.core.network.v4.model.ref.OrganisationReferenceDto
import com.mss.core.network.v4.model.ref.create

fun SeriesDto.Companion.create(
    name: String = "series name",
    shortName: String? = "series shortName",
    shortCode: String = "series shortCode",
    uuid: String = "series uuid",
    slug: String = "series slug",
    picture: String? = "series picture",
    status: String = "series status",
    firstSeason: String = "series firstSeason",
    firstFullSeasonYear: String? = "series firstFullSeasonYear",
    organisation: OrganisationReferenceDto? = OrganisationReferenceDto.create(),
    headquarters: SeriesDto.Headquarters? = SeriesDto.Headquarters.create(),
    region: String = "series region",
    template: TemplateDto = TemplateDto.create(),
    resourceLinks: List<ResourceLinkDto> = emptyList(),
) = SeriesDto(
    name = name,
    shortName = shortName,
    shortCode = shortCode,
    uuid = uuid,
    slug = slug,
    picture = picture,
    status = status,
    firstSeason = firstSeason,
    firstFullSeasonYear = firstFullSeasonYear,
    organisation = organisation,
    headquarters = headquarters,
    region = region,
    template = template,
    resourceLinks = resourceLinks
)

fun SeriesDto.Headquarters.Companion.create(
    address: String = "headquarter address"
) = SeriesDto.Headquarters(
    address = address
)