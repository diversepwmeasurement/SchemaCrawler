/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2020, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/

package schemacrawler.schemacrawler;


import java.util.Optional;
import java.util.regex.Pattern;
import schemacrawler.inclusionrule.InclusionRule;
import schemacrawler.inclusionrule.RegularExpressionInclusionRule;

/**
 * grep options builder, to build the immutable options to crawl a schema.
 */
public final class GrepOptionsBuilder
  implements OptionsBuilder<GrepOptionsBuilder, GrepOptions>
{

  public static GrepOptionsBuilder builder()
  {
    return new GrepOptionsBuilder();
  }

  public static GrepOptions newGrepOptions()
  {
    return builder().toOptions();
  }

  private Optional<InclusionRule> grepColumnInclusionRule;
  private Optional<InclusionRule> grepDefinitionInclusionRule;
  private boolean grepInvertMatch;
  private boolean grepOnlyMatching;
  private Optional<InclusionRule> grepRoutineParameterInclusionRule;

  /**
   * Default options.
   */
  private GrepOptionsBuilder()
  {
    grepColumnInclusionRule = Optional.empty();
    grepRoutineParameterInclusionRule = Optional.empty();
    grepDefinitionInclusionRule = Optional.empty();
  }

  @Override
  public GrepOptionsBuilder fromOptions(final GrepOptions options)
  {
    if (options == null)
    {
      return this;
    }

    grepColumnInclusionRule = options.getGrepColumnInclusionRule();
    grepRoutineParameterInclusionRule = Optional
      .ofNullable(options.getGrepRoutineParameterInclusionRule())
      .orElse(null);
    grepDefinitionInclusionRule = Optional
      .ofNullable(options.getGrepDefinitionInclusionRule())
      .orElse(null);
    grepInvertMatch = options.isGrepInvertMatch();
    grepOnlyMatching = options.isGrepOnlyMatching();

    return this;
  }

  @Override
  public GrepOptions toOptions()
  {
    final GrepOptions grepOptions =
      new GrepOptions(grepColumnInclusionRule.orElse(null),
                      grepRoutineParameterInclusionRule.orElse(null),
                      grepDefinitionInclusionRule.orElse(null),
                      grepInvertMatch,
                      grepOnlyMatching);

    return grepOptions;
  }

  public GrepOptionsBuilder grepOnlyMatching(final boolean grepOnlyMatching)
  {
    this.grepOnlyMatching = grepOnlyMatching;
    return this;
  }

  public GrepOptionsBuilder includeGreppedColumns(final InclusionRule grepColumnInclusionRule)
  {
    this.grepColumnInclusionRule = Optional.ofNullable(grepColumnInclusionRule);
    return this;
  }

  public GrepOptionsBuilder includeGreppedColumns(final Pattern grepColumnPattern)
  {
    if (grepColumnPattern == null)
    {
      grepColumnInclusionRule = Optional.empty();
    }
    else
    {
      grepColumnInclusionRule =
        Optional.of(new RegularExpressionInclusionRule(grepColumnPattern));
    }
    return this;
  }

  public GrepOptionsBuilder includeGreppedDefinitions(final InclusionRule grepDefinitionInclusionRule)
  {
    this.grepDefinitionInclusionRule =
      Optional.ofNullable(grepDefinitionInclusionRule);
    return this;
  }

  public GrepOptionsBuilder includeGreppedDefinitions(final Pattern grepDefinitionPattern)
  {
    if (grepDefinitionPattern == null)
    {
      grepDefinitionInclusionRule = Optional.empty();
    }
    else
    {
      grepDefinitionInclusionRule =
        Optional.of(new RegularExpressionInclusionRule(grepDefinitionPattern));
    }
    return this;
  }

  public GrepOptionsBuilder includeGreppedRoutineParameters(final InclusionRule grepRoutineParameterInclusionRule)
  {
    this.grepRoutineParameterInclusionRule =
      Optional.ofNullable(grepRoutineParameterInclusionRule);
    return this;
  }

  public GrepOptionsBuilder includeGreppedRoutineParameters(final Pattern grepRoutineParametersPattern)
  {
    if (grepRoutineParametersPattern == null)
    {
      grepRoutineParameterInclusionRule = Optional.empty();
    }
    else
    {
      grepRoutineParameterInclusionRule =
        Optional.of(new RegularExpressionInclusionRule(
          grepRoutineParametersPattern));
    }
    return this;
  }

  public GrepOptionsBuilder invertGrepMatch(final boolean grepInvertMatch)
  {
    this.grepInvertMatch = grepInvertMatch;
    return this;
  }

}
